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

import java.util.Map.Entry;

import com.l2jserver.gameserver.model.effects.EffectTemplate;
import com.l2jserver.gameserver.model.effects.L2Effect;
import com.l2jserver.gameserver.model.effects.L2EffectType;
import com.l2jserver.gameserver.model.skills.AbnormalType;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.stats.Env;

/**
 * Dispel By Slot effect.
 * @author Gnacik, Zoey76
 */
public class DispelBySlot extends L2Effect
{
	public DispelBySlot(Env env, EffectTemplate template)
	{
		super(env, template);
	}
	
	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.NEGATE;
	}
	
	@Override
	public boolean onActionTime()
	{
		return false;
	}
	
	@Override
	public boolean onStart()
	{
		final L2Skill skill = getSkill();
		if (skill.getNegateAbnormals().isEmpty())
		{
			return false;
		}
		
		for (L2Effect effect : getEffected().getAllEffects())
		{
			if (effect == null)
			{
				continue;
			}
			
			for (Entry<AbnormalType, Byte> negate : skill.getNegateAbnormals().entrySet())
			{
				if ((effect.getSkill().getAbnormalType() == negate.getKey()) && (negate.getValue() >= effect.getSkill().getAbnormalLvl()))
				{
					effect.exit();
				}
			}
		}
		return true;
	}
}