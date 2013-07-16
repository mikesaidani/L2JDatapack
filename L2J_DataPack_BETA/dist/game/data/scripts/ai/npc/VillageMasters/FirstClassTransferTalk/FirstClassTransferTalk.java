/*
 * Copyright (C) 2004-2013 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ai.npc.VillageMasters.FirstClassTransferTalk;

import java.util.HashMap;
import java.util.Map;

import ai.npc.AbstractNpcAI;

import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.actor.instance.L2VillageMasterFighterInstance;
import com.l2jserver.gameserver.model.actor.instance.L2VillageMasterPriestInstance;
import com.l2jserver.gameserver.model.base.Race;

/**
 * This script manages the dialogs of the headmasters of all newbie villages.<br>
 * None of them provide actual class transfers, they only talk about it.
 * @author jurchiks, xban1x
 */
public final class FirstClassTransferTalk extends AbstractNpcAI
{
	private static final Map<Integer, Race> MASTERS = new HashMap<>();
	static
	{
		MASTERS.put(30026, Race.Human); // Blitz, TI Fighter Guild Head Master
		MASTERS.put(30031, Race.Human); // Biotin, TI Einhasad Temple High Priest
		MASTERS.put(30154, Race.Elf); // Asterios, Elven Village Tetrarch
		MASTERS.put(30358, Race.DarkElf); // Thifiell, Dark Elf Village Tetrarch
		MASTERS.put(30565, Race.Orc); // Kakai, Orc Village Flame Lord
		MASTERS.put(30520, Race.Dwarf); // Reed, Dwarven Village Warehouse Chief
		MASTERS.put(30525, Race.Dwarf); // Bronk, Dwarven Village Head Blacksmith
		// Kamael Village NPCs
		MASTERS.put(32171, Race.Dwarf); // Hoffa, Warehouse Chief
		MASTERS.put(32158, Race.Dwarf); // Fisler, Dwarf Guild Warehouse Chief
		MASTERS.put(32157, Race.Dwarf); // Moka, Dwarf Guild Head Blacksmith
		MASTERS.put(32160, Race.DarkElf); // Devon, Dark Elf Guild Grand Magister
		MASTERS.put(32147, Race.Elf); // Rivian, Elf Guild Grand Master
		MASTERS.put(32150, Race.Orc); // Took, Orc Guild High Prefect
		MASTERS.put(32153, Race.Human); // Prana, Human Guild High Priest
		MASTERS.put(32154, Race.Human); // Aldenia, Human Guild Grand Master
	}
	
	private FirstClassTransferTalk(String name, String descr)
	{
		super(name, descr);
		addStartNpc(MASTERS.keySet());
		addTalkId(MASTERS.keySet());
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		return event;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = npc.getNpcId() + "_";
		
		if (MASTERS.get(npc.getNpcId()) != player.getRace())
		{
			return htmltext += "no.html";
		}
		
		switch (MASTERS.get(npc.getNpcId()))
		{
			case Human:
			{
				if (player.getClassId().level() == 0)
				{
					if (player.isMageClass())
					{
						if (npc instanceof L2VillageMasterPriestInstance)
						{
							htmltext += "mystic.html";
						}
					}
					else
					{
						if (npc instanceof L2VillageMasterFighterInstance)
						{
							htmltext += "fighter.html";
						}
					}
				}
				else if (player.getClassId().level() == 1)
				{
					htmltext += "transfer_1.html";
				}
				else
				{
					htmltext += "transfer_2.html";
				}
				break;
			}
			case Elf:
			case DarkElf:
			case Orc:
			{
				if (player.getClassId().level() == 0)
				{
					if (player.isMageClass())
					{
						htmltext += "mystic.html";
					}
					else
					{
						htmltext += "fighter.html";
					}
				}
				else if (player.getClassId().level() == 1)
				{
					htmltext += "transfer_1.html";
				}
				else
				{
					htmltext += "transfer_2.html";
				}
				break;
			}
			case Dwarf:
			{
				if (player.getClassId().level() == 0)
				{
					htmltext += "fighter.html";
				}
				else if (player.getClassId().level() == 1)
				{
					htmltext += "transfer_1.html";
				}
				else
				{
					htmltext += "transfer_2.html";
				}
				break;
			}
			default:
			{
				htmltext += "no.html";
				break;
			}
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new FirstClassTransferTalk(FirstClassTransferTalk.class.getSimpleName(), "ai/npc/VillageMasters");
	}
}
