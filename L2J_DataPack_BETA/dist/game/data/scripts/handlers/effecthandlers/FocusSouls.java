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

import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.effects.EffectTemplate;
import com.l2jserver.gameserver.model.effects.L2Effect;
import com.l2jserver.gameserver.model.effects.L2EffectType;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.stats.Env;
import com.l2jserver.gameserver.network.SystemMessageId;

/**
 * Focus Souls effect implementation.
 * @author nBd, Adry_85
 */
public class FocusSouls extends L2Effect
{
	public FocusSouls(Env env, EffectTemplate template)
	{
		super(env, template);
	}
	
	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.FOCUS_SOULS;
	}
	
	@Override
	public boolean onStart()
	{
		if (!getEffected().isPlayer() || getEffected().isAlikeDead())
		{
			return false;
		}
		
		final L2PcInstance target = getEffected().getActingPlayer();
		final L2Skill soulMastery = target.getKnownSkill(L2Skill.SKILL_SOUL_MASTERY);
		if (soulMastery != null)
		{
			int amount = (int) calc();
			if ((target.getChargedSouls() < soulMastery.getNumSouls()))
			{
				int count = ((target.getChargedSouls() + amount) <= soulMastery.getNumSouls()) ? amount : (soulMastery.getNumSouls() - target.getChargedSouls());
				target.increaseSouls(count);
			}
			else
			{
				target.sendPacket(SystemMessageId.SOUL_CANNOT_BE_INCREASED_ANYMORE);
				return false;
			}
		}
		return true;
	}
}