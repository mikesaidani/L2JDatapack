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

import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.events.listeners.IDamageReceivedEventListener;
import com.l2jserver.gameserver.model.effects.EffectTemplate;
import com.l2jserver.gameserver.model.effects.L2Effect;
import com.l2jserver.gameserver.model.effects.L2EffectType;
import com.l2jserver.gameserver.model.holders.SkillHolder;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.stats.Env;
import com.l2jserver.util.Rnd;

/**
 * Trigger Skill By Damage effect implementation.
 * @author UnAfraid
 */
public class TriggerSkillByDamage extends L2Effect implements IDamageReceivedEventListener
{
	private final int _minLevel;
	private final int _maxLevel;
	private final int _minDamage;
	private final int _chance;
	private final SkillHolder _skill;
	
	public TriggerSkillByDamage(Env env, EffectTemplate template)
	{
		super(env, template);
		_minLevel = template.getParameters().getInt("minLevel", 1);
		_maxLevel = template.getParameters().getInt("maxLevel", 100);
		_minDamage = template.getParameters().getInt("minDamage", 1);
		_chance = template.getParameters().getInt("chance", 100);
		_skill = new SkillHolder(template.getParameters().getInt("skillId"), template.getParameters().getInt("skillLevel", 1));
	}
	
	public TriggerSkillByDamage(Env env, L2Effect effect)
	{
		super(env, effect);
		_minLevel = effect.getEffectTemplate().getParameters().getInt("minLevel", 1);
		_maxLevel = effect.getEffectTemplate().getParameters().getInt("maxLevel", 100);
		_minDamage = effect.getEffectTemplate().getParameters().getInt("minDamage", 1);
		_chance = effect.getEffectTemplate().getParameters().getInt("chance", 100);
		_skill = new SkillHolder(effect.getEffectTemplate().getParameters().getInt("skillId"), effect.getEffectTemplate().getParameters().getInt("skillLevel", 1));
	}
	
	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.NONE;
	}
	
	@Override
	public void onDamageReceivedEvent(L2Character attacker, L2Character target, double damage, L2Skill skill, boolean crit)
	{
		int level = getEffected().getLevel();
		if (!getEffected().isInvul() && (level >= _minLevel) && (level <= _maxLevel) && (damage >= _minDamage) && (Rnd.get(100) < _chance))
		{
			_skill.getSkill().getEffects(getEffected(), getEffected());
		}
	}
	
	@Override
	public void onExit()
	{
		getEffected().getEvents().unregisterListener(this);
		super.onExit();
	}
	
	@Override
	public boolean onStart()
	{
		getEffected().getEvents().registerListener(this);
		return super.onStart();
	}
}
