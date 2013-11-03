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
package handlers.effecthandlers;

import com.l2jserver.gameserver.ai.CtrlIntention;
import com.l2jserver.gameserver.model.StatsSet;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.conditions.Condition;
import com.l2jserver.gameserver.model.effects.AbstractEffect;
import com.l2jserver.gameserver.model.skills.AbnormalVisualEffect;
import com.l2jserver.gameserver.model.skills.BuffInfo;
import com.l2jserver.gameserver.network.serverpackets.DeleteObject;
import com.l2jserver.gameserver.network.serverpackets.L2GameServerPacket;

/**
 * Hide effect implementation.
 * @author ZaKaX, nBd
 */
public final class Hide extends AbstractEffect
{
	public Hide(Condition attachCond, Condition applyCond, StatsSet set, StatsSet params)
	{
		super(attachCond, applyCond, set, params);
	}
	
	@Override
	public void onExit(BuffInfo info)
	{
		if (info.getEffected().isPlayer())
		{
			L2PcInstance activeChar = info.getEffected().getActingPlayer();
			if (!activeChar.inObserverMode())
			{
				activeChar.getAppearance().setVisible();
			}
			activeChar.stopAbnormalEffect(AbnormalVisualEffect.STEALTH);
		}
	}
	
	@Override
	public void onStart(BuffInfo info)
	{
		if (info.getEffected().isPlayer())
		{
			L2PcInstance activeChar = info.getEffected().getActingPlayer();
			activeChar.getAppearance().setInvisible();
			activeChar.startAbnormalEffect(AbnormalVisualEffect.STEALTH);
			
			if ((activeChar.getAI().getNextIntention() != null) && (activeChar.getAI().getNextIntention().getCtrlIntention() == CtrlIntention.AI_INTENTION_ATTACK))
			{
				activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
			}
			
			L2GameServerPacket del = new DeleteObject(activeChar);
			for (L2Character target : activeChar.getKnownList().getKnownCharacters())
			{
				try
				{
					if (target.getTarget() == activeChar)
					{
						target.setTarget(null);
						target.abortAttack();
						target.abortCast();
						target.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
					}
					
					if (target.isPlayer())
					{
						target.sendPacket(del);
					}
				}
				catch (NullPointerException e)
				{
				}
			}
		}
	}
}