/**
 * 
 */
package handlers;

import com.l2jserver.gameserver.handler.EffectHandler;

import handlers.effecthandlers.*;

/**
 * @author BiggBoss
 */
public final class EffectMasterHandler
{
	private static void loadEffectHandlers()
	{
		EffectHandler.getInstance().registerHandler("AbortCast", EffectAbortCast.class);
		EffectHandler.getInstance().registerHandler("Betray", EffectBetray.class);
		EffectHandler.getInstance().registerHandler("BigHead", EffectBigHead.class);
		EffectHandler.getInstance().registerHandler("BlockResurrection", EffectBlockResurrection.class);
		EffectHandler.getInstance().registerHandler("Bluff", EffectBluff.class);
		EffectHandler.getInstance().registerHandler("Buff", EffectBuff.class);
		EffectHandler.getInstance().registerHandler("Cancel", EffectCancel.class);
		EffectHandler.getInstance().registerHandler("CancelAll", EffectCancelAll.class);
		EffectHandler.getInstance().registerHandler("CancelDebuff", EffectCancelDebuff.class);
		EffectHandler.getInstance().registerHandler("ChameleonRest", EffectChameleonRest.class);
		EffectHandler.getInstance().registerHandler("ChanceSkillTrigger", EffectChanceSkillTrigger.class);
		EffectHandler.getInstance().registerHandler("CharmOfCourage", EffectCharmOfCourage.class);
		EffectHandler.getInstance().registerHandler("CharmOfLuck", EffectCharmOfLuck.class);
		EffectHandler.getInstance().registerHandler("ClanGate", EffectClanGate.class);
		EffectHandler.getInstance().registerHandler("CombatPointHealOverTime", EffectCombatPointHealOverTime.class);
		EffectHandler.getInstance().registerHandler("ConfuseMob", EffectConfuseMob.class);
		EffectHandler.getInstance().registerHandler("Confusion", EffectConfusion.class);
		EffectHandler.getInstance().registerHandler("CpDamPercent", EffectCpDamPercent.class);
		EffectHandler.getInstance().registerHandler("DamOverTime", EffectDamOverTime.class);
		EffectHandler.getInstance().registerHandler("Debuff", EffectDebuff.class);
		EffectHandler.getInstance().registerHandler("Disarm", EffectDisarm.class);
		EffectHandler.getInstance().registerHandler("EnemyCharge", EffectEnemyCharge.class);
		EffectHandler.getInstance().registerHandler("FakeDeath", EffectFakeDeath.class);
		EffectHandler.getInstance().registerHandler("Fear", EffectFear.class);
		EffectHandler.getInstance().registerHandler("Fusion", EffectFusion.class);
		EffectHandler.getInstance().registerHandler("Grow", EffectGrow.class);
		EffectHandler.getInstance().registerHandler("HealOverTime", EffectHealOverTime.class);
		EffectHandler.getInstance().registerHandler("Hide", EffectHide.class);
		EffectHandler.getInstance().registerHandler("ImmobileBuff", EffectImmobileBuff.class);
		EffectHandler.getInstance().registerHandler("IncreaseCharges", EffectIncreaseCharges.class);
		EffectHandler.getInstance().registerHandler("ImmobilePetBuff", EffectImmobilePetBuff.class);
		EffectHandler.getInstance().registerHandler("Invincible", EffectInvincible.class);
		EffectHandler.getInstance().registerHandler("ManaDamOverTime", EffectManaDamOverTime.class);
		EffectHandler.getInstance().registerHandler("ManaHealOverTime", EffectManaHealOverTime.class);
		EffectHandler.getInstance().registerHandler("MpConsumePerLevel", EffectMpConsumePerLevel.class);
		EffectHandler.getInstance().registerHandler("Mute", EffectMute.class);
		EffectHandler.getInstance().registerHandler("Negate", EffectNegate.class);
		EffectHandler.getInstance().registerHandler("NoblesseBless", EffectNoblesseBless.class);
		EffectHandler.getInstance().registerHandler("Paralyze", EffectParalyze.class);
		EffectHandler.getInstance().registerHandler("Petrification", EffectPetrification.class);
		EffectHandler.getInstance().registerHandler("PhoenixBless", EffectPhoenixBless.class);
		EffectHandler.getInstance().registerHandler("PhysicalAttackMute", EffectPhysicalAttackMute.class);
		EffectHandler.getInstance().registerHandler("PhysicalMute", EffectPhysicalMute.class);
		EffectHandler.getInstance().registerHandler("ProtectionBlessing", EffectProtectionBlessing.class);
		EffectHandler.getInstance().registerHandler("RandomizeHate", EffectRandomizeHate.class);
		EffectHandler.getInstance().registerHandler("Recovery", EffectRecovery.class);
		EffectHandler.getInstance().registerHandler("Relax", EffectRelax.class);
		EffectHandler.getInstance().registerHandler("RemoveTarget", EffectRemoveTarget.class);
		EffectHandler.getInstance().registerHandler("Root", EffectRoot.class);
		EffectHandler.getInstance().registerHandler("Signet", EffectSignet.class);
		EffectHandler.getInstance().registerHandler("SignetAntiSummon", EffectSignetAntiSummon.class);
		EffectHandler.getInstance().registerHandler("SignetMDam", EffectSignetMDam.class);
		EffectHandler.getInstance().registerHandler("SignetNoise", EffectSignetNoise.class);
		EffectHandler.getInstance().registerHandler("SilenceMagicPhysical", EffectSilenceMagicPhysical.class);
		EffectHandler.getInstance().registerHandler("SilentMove", EffectSilentMove.class);
		EffectHandler.getInstance().registerHandler("Sleep", EffectSleep.class);
		EffectHandler.getInstance().registerHandler("Spoil", EffectSpoil.class);
		EffectHandler.getInstance().registerHandler("Stun", EffectStun.class);
		EffectHandler.getInstance().registerHandler("StunSelf", EffectStunSelf.class);
		EffectHandler.getInstance().registerHandler("TargetMe", EffectTargetMe.class);
		EffectHandler.getInstance().registerHandler("ThrowUp", EffectThrowUp.class);
		EffectHandler.getInstance().registerHandler("TransferDamage", EffectTransferDamage.class);
		EffectHandler.getInstance().registerHandler("Transformation", EffectTransformation.class);
		EffectHandler.getInstance().registerHandler("Warp", EffectWarp.class);
	}
	
	public static void main(String[] args)
	{
		loadEffectHandlers();
	}
}
