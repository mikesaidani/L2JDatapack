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

import com.l2jserver.gameserver.model.actor.L2Attackable;
import com.l2jserver.gameserver.model.effects.EffectTemplate;
import com.l2jserver.gameserver.model.effects.L2Effect;
import com.l2jserver.gameserver.model.effects.L2EffectType;
import com.l2jserver.gameserver.model.stats.Env;

/**
 * Add Hate effect implementation.
 * @author Adry_85
 */
public class AddHate extends L2Effect
{
	public AddHate(Env env, EffectTemplate template)
	{
		super(env, template);
	}
	
	@Override
	public boolean calcSuccess()
	{
		return true;
	}
	
	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.NONE;
	}
	
	@Override
	public boolean isInstant()
	{
		return true;
	}
	
	@Override
	public boolean onStart()
	{
		if (!getEffected().isL2Attackable())
		{
			return false;
		}
		
		if (calc() > 0)
		{
			((L2Attackable) getEffected()).addDamageHate(getEffector(), 0, (int) calc());
		}
		else if (calc() < 0)
		{
			((L2Attackable) getEffected()).reduceHate(getEffector(), (int) -calc());
		}
		return true;
	}
}
