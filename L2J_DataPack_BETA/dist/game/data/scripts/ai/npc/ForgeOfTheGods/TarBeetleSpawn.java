/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ai.npc.ForgeOfTheGods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javolution.util.FastMap;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jserver.gameserver.GeoData;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.NpcTable;
import com.l2jserver.gameserver.engines.DocumentParser;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.L2Territory;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.util.Rnd;

/**
 * Tar Beetle zone spawn
 * @author malyelfik
 */
public class TarBeetleSpawn extends DocumentParser
{
	private static final Map<Integer, SpawnZone> _spawnZoneList = new HashMap<>();
	private static final Map<Integer, Beetle> _spawnList = new FastMap<>();
	
	public static List<Integer> lowerZones = new ArrayList<>();
	public static List<Integer> upperZones = new ArrayList<>();
	
	public static int lowerNpcCount = 0;
	public static int upperNpcCount = 0;
	
	public TarBeetleSpawn()
	{
		load();
	}
	
	@Override
	public void load()
	{
		_spawnZoneList.clear();
		_spawnList.clear();
		parseDatapackFile("data/spawnZones/tar_bettle.xml");
		_log.info(TarBeetleSpawn.class.getSimpleName() + ": Loaded " + _spawnZoneList.size() + " spawn zones.");
	}
	
	@Override
	protected void parseDocument()
	{
		final Node n = getCurrentDocument().getFirstChild();
		for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
		{
			if (d.getNodeName().equals("spawnZones"))
			{
				for (Node r = d.getFirstChild(); r != null; r = r.getNextSibling())
				{
					if (r.getNodeName().equals("zone"))
					{
						NamedNodeMap attrs = r.getAttributes();
						int id = parseInt(attrs, "id");
						int minZ = parseInt(attrs, "minZ");
						int maxZ = parseInt(attrs, "maxZ");
						String type = parseString(attrs, "type");
						if (type.equals("upper"))
						{
							upperZones.add(id);
						}
						else if (type.equals("lower"))
						{
							lowerZones.add(id);
						}
						
						int[] bZones = null;
						String bZonesStr = parseString(attrs, "bZones");
						if (!bZonesStr.isEmpty())
						{
							String[] str = bZonesStr.split(";");
							bZones = new int[str.length];
							for (int i = 0; i < str.length; i++)
							{
								bZones[i] = Integer.parseInt(str[i]);
							}
						}
						
						SpawnZone zone = new SpawnZone(id, bZones);
						for (Node c = r.getFirstChild(); c != null; c = c.getNextSibling())
						{
							if (c.getNodeName().equals("point"))
							{
								attrs = c.getAttributes();
								int x = parseInt(attrs, "x");
								int y = parseInt(attrs, "y");
								zone.add(x, y, minZ, maxZ, 0);
							}
						}
						_spawnZoneList.put(id, zone);
					}
				}
			}
		}
	}
	
	public void removeBeetle(L2Npc npc)
	{
		npc.deleteMe();
		_spawnList.remove(npc.getObjectId());
		if (npc.getSpawn().getLocz() < -5000)
		{
			lowerNpcCount--;
		}
		else
		{
			upperNpcCount--;
		}
	}
	
	public void spawn(List<Integer> zone)
	{
		try
		{
			Collections.shuffle(zone);
			int[] loc = getSpawnZoneById(zone.get(0)).getRandomPoint();
			
			final L2Spawn spawn = new L2Spawn(NpcTable.getInstance().getTemplate(18804));
			spawn.setHeading(Rnd.get(65535));
			spawn.setLocx(loc[0]);
			spawn.setLocy(loc[1]);
			spawn.setLocz(GeoData.getInstance().getSpawnHeight(loc[0], loc[1], loc[2], loc[3], null));
			
			final L2Npc npc = spawn.doSpawn();
			npc.setIsNoRndWalk(true);
			npc.setIsImmobilized(true);
			npc.setIsInvul(true);
			npc.disableCoreAI(true);
			
			_spawnList.put(npc.getObjectId(), new Beetle(npc));
		}
		catch (Exception e)
		{
			_log.warning(TarBeetleSpawn.class.getSimpleName() + ": Could not spawn npc! Error: " + e.getMessage());
		}
	}
	
	public void startTasks()
	{
		ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new SpawnTask(), 1000, 60000);
		ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new NumShotTask(), 300000, 300000);
	}
	
	public SpawnZone getSpawnZoneById(int id)
	{
		return _spawnZoneList.get(id);
	}
	
	public Beetle getBeetle(L2Npc npc)
	{
		return _spawnList.get(npc.getObjectId());
	}
	
	public static Map<Integer, Beetle> getSpawnList()
	{
		return _spawnList;
	}
	
	public class Beetle
	{
		private int _numShotValue = 5;
		private final L2Npc _npc;
		
		public Beetle(L2Npc npc)
		{
			_npc = npc;
		}
		
		public L2Npc getNpc()
		{
			return _npc;
		}
		
		public void setNumShotValue(int numShotValue)
		{
			_numShotValue = numShotValue;
		}
		
		public int getNumShotValue()
		{
			return _numShotValue;
		}
	}
	
	private class SpawnZone extends L2Territory
	{
		private int[] _bZones;
		
		public SpawnZone(int terr, int[] bZones)
		{
			super(terr);
			_bZones = bZones;
		}
		
		@Override
		public int[] getRandomPoint()
		{
			int[] loc = super.getRandomPoint();
			while (isInsideBannedZone(loc))
			{
				loc = super.getRandomPoint();
			}
			return loc;
		}
		
		private boolean isInsideBannedZone(int[] loc)
		{
			if (_bZones != null)
			{
				for (int i : _bZones)
				{
					if (getSpawnZoneById(i).isInside(loc[0], loc[1]))
					{
						return true;
					}
				}
			}
			return false;
		}
	}
	
	public class SpawnTask implements Runnable
	{
		@Override
		public void run()
		{
			while (lowerNpcCount < 4)
			{
				spawn(lowerZones);
				lowerNpcCount++;
			}
			
			while (upperNpcCount < 12)
			{
				spawn(upperZones);
				upperNpcCount++;
			}
		}
	}
	
	public class NumShotTask implements Runnable
	{
		@Override
		public void run()
		{
			Iterator<Beetle> iterator = getSpawnList().values().iterator();
			while (iterator.hasNext())
			{
				Beetle b = iterator.next();
				int val = b.getNumShotValue();
				if (val == 5)
				{
					b.getNpc().deleteMe();
					iterator.remove();
					if (b.getNpc().getSpawn().getLocz() < -5000)
					{
						lowerNpcCount--;
					}
					else
					{
						upperNpcCount--;
					}
				}
				else
				{
					b.setNumShotValue(val + 1);
				}
			}
		}
	}
}