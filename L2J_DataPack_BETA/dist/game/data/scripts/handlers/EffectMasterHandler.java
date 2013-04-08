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
package handlers;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jserver.gameserver.handler.EffectHandler;

import handlers.effecthandlers.AbortCast;
import handlers.effecthandlers.Betray;
import handlers.effecthandlers.BigHead;
import handlers.effecthandlers.BlockResurrection;
import handlers.effecthandlers.Bluff;
import handlers.effecthandlers.Buff;
import handlers.effecthandlers.Cancel;
import handlers.effecthandlers.CancelAll;
import handlers.effecthandlers.CancelDebuff;
import handlers.effecthandlers.ChameleonRest;
import handlers.effecthandlers.ChanceSkillTrigger;
import handlers.effecthandlers.ChangeFace;
import handlers.effecthandlers.ChangeHairColor;
import handlers.effecthandlers.ChangeHairStyle;
import handlers.effecthandlers.CharmOfCourage;
import handlers.effecthandlers.CharmOfLuck;
import handlers.effecthandlers.ClanGate;
import handlers.effecthandlers.ConfuseMob;
import handlers.effecthandlers.Confusion;
import handlers.effecthandlers.ConsumeBody;
import handlers.effecthandlers.ConvertItem;
import handlers.effecthandlers.CpDamPercent;
import handlers.effecthandlers.CpHeal;
import handlers.effecthandlers.CpHealOverTime;
import handlers.effecthandlers.CpHealPercent;
import handlers.effecthandlers.CrystalGradeModify;
import handlers.effecthandlers.DamOverTime;
import handlers.effecthandlers.DamOverTimePercent;
import handlers.effecthandlers.Debuff;
import handlers.effecthandlers.Disarm;
import handlers.effecthandlers.DispelBySlot;
import handlers.effecthandlers.EnemyCharge;
import handlers.effecthandlers.EnlargeAbnormalSlot;
import handlers.effecthandlers.FakeDeath;
import handlers.effecthandlers.Fear;
import handlers.effecthandlers.FocusSouls;
import handlers.effecthandlers.Fusion;
import handlers.effecthandlers.GiveSp;
import handlers.effecthandlers.Grow;
import handlers.effecthandlers.Harvesting;
import handlers.effecthandlers.Heal;
import handlers.effecthandlers.HealOverTime;
import handlers.effecthandlers.HealPercent;
import handlers.effecthandlers.Hide;
import handlers.effecthandlers.HpByLevel;
import handlers.effecthandlers.ImmobileBuff;
import handlers.effecthandlers.ImmobilePetBuff;
import handlers.effecthandlers.IncreaseCharges;
import handlers.effecthandlers.Invincible;
import handlers.effecthandlers.Lucky;
import handlers.effecthandlers.ManaDamOverTime;
import handlers.effecthandlers.ManaHeal;
import handlers.effecthandlers.ManaHealByLevel;
import handlers.effecthandlers.ManaHealOverTime;
import handlers.effecthandlers.ManaHealPercent;
import handlers.effecthandlers.MpByLevel;
import handlers.effecthandlers.MpConsumePerLevel;
import handlers.effecthandlers.Mute;
import handlers.effecthandlers.Negate;
import handlers.effecthandlers.NoblesseBless;
import handlers.effecthandlers.Paralyze;
import handlers.effecthandlers.Petrification;
import handlers.effecthandlers.PhoenixBless;
import handlers.effecthandlers.PhysicalAttackMute;
import handlers.effecthandlers.PhysicalMute;
import handlers.effecthandlers.ProtectionBlessing;
import handlers.effecthandlers.RandomizeHate;
import handlers.effecthandlers.RebalanceHP;
import handlers.effecthandlers.Recovery;
import handlers.effecthandlers.Relax;
import handlers.effecthandlers.RemoveTarget;
import handlers.effecthandlers.RestorationRandom;
import handlers.effecthandlers.Root;
import handlers.effecthandlers.ServitorShare;
import handlers.effecthandlers.Signet;
import handlers.effecthandlers.SignetAntiSummon;
import handlers.effecthandlers.SignetMDam;
import handlers.effecthandlers.SignetNoise;
import handlers.effecthandlers.SilentMove;
import handlers.effecthandlers.Sleep;
import handlers.effecthandlers.Spoil;
import handlers.effecthandlers.Stun;
import handlers.effecthandlers.SummonAgathion;
import handlers.effecthandlers.SummonPet;
import handlers.effecthandlers.Sweeper;
import handlers.effecthandlers.TargetMe;
import handlers.effecthandlers.ThrowUp;
import handlers.effecthandlers.TransferDamage;
import handlers.effecthandlers.Transformation;
import handlers.effecthandlers.UnsummonAgathion;
import handlers.effecthandlers.Warp;

/**
 * Effect Master handler.
 * @author BiggBoss
 */
public final class EffectMasterHandler
{
	private static final Logger _log = Logger.getLogger(EffectMasterHandler.class.getName());
	
	private static final Class<?> _loadInstances = EffectHandler.class;
	
	private static final Class<?>[] _effects =
	{
		AbortCast.class,
		RebalanceHP.class,
		Betray.class,
		BigHead.class,
		BlockResurrection.class,
		Bluff.class,
		Buff.class,
		Cancel.class,
		CancelAll.class,
		CancelDebuff.class,
		ChameleonRest.class,
		ChanceSkillTrigger.class,
		ChangeFace.class,
		ChangeHairColor.class,
		ChangeHairStyle.class,
		CharmOfCourage.class,
		CharmOfLuck.class,
		ClanGate.class,
		ConfuseMob.class,
		Confusion.class,
		ConsumeBody.class,
		ConvertItem.class,
		CpHeal.class,
		CpHealOverTime.class,
		CpHealPercent.class,
		CrystalGradeModify.class,
		CpDamPercent.class,
		DamOverTime.class,
		DamOverTimePercent.class,
		Debuff.class,
		DispelBySlot.class,
		Disarm.class,
		EnemyCharge.class,
		EnlargeAbnormalSlot.class,
		FakeDeath.class,
		Fear.class,
		FocusSouls.class,
		Fusion.class,
		GiveSp.class,
		Grow.class,
		Harvesting.class,
		HealOverTime.class,
		HealPercent.class,
		Heal.class,
		Hide.class,
		HpByLevel.class,
		ImmobileBuff.class,
		IncreaseCharges.class,
		ImmobilePetBuff.class,
		Invincible.class,
		Lucky.class,
		ManaDamOverTime.class,
		ManaHeal.class,
		ManaHealByLevel.class,
		ManaHealOverTime.class,
		ManaHealPercent.class,
		MpByLevel.class,
		MpConsumePerLevel.class,
		Mute.class,
		Negate.class,
		NoblesseBless.class,
		Paralyze.class,
		Petrification.class,
		PhoenixBless.class,
		PhysicalAttackMute.class,
		PhysicalMute.class,
		ProtectionBlessing.class,
		RandomizeHate.class,
		Recovery.class,
		Relax.class,
		RemoveTarget.class,
		RestorationRandom.class,
		Root.class,
		ServitorShare.class,
		Signet.class,
		SignetAntiSummon.class,
		SignetMDam.class,
		SignetNoise.class,
		SilentMove.class,
		Sleep.class,
		Spoil.class,
		Stun.class,
		SummonAgathion.class,
		SummonPet.class,
		Sweeper.class,
		TargetMe.class,
		ThrowUp.class,
		TransferDamage.class,
		Transformation.class,
		UnsummonAgathion.class,
		Warp.class,
	};
	
	public static void main(String[] args)
	{
		Object loadInstance = null;
		Method method = null;
		
		try
		{
			method = _loadInstances.getMethod("getInstance");
			loadInstance = method.invoke(_loadInstances);
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Failed invoking getInstance method for handler: " + _loadInstances.getSimpleName(), e);
			return;
		}
		
		method = null; // Releasing variable for next method
		
		for (Class<?> c : _effects)
		{
			try
			{
				if (c == null)
				{
					continue; // Disabled handler
				}
				
				if (method == null)
				{
					method = loadInstance.getClass().getMethod("registerHandler", Class.class);
				}
				
				method.invoke(loadInstance, c);
				
			}
			catch (Exception e)
			{
				_log.log(Level.WARNING, "Failed loading effect handler: " + c.getSimpleName(), e);
				continue;
			}
		}
		
		// And lets try get size
		try
		{
			method = loadInstance.getClass().getMethod("size");
			Object returnVal = method.invoke(loadInstance);
			_log.log(Level.INFO, loadInstance.getClass().getSimpleName() + ": Loaded " + returnVal + " Handlers");
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Failed invoking size method for handler: " + loadInstance.getClass().getSimpleName(), e);
		}
	}
}
