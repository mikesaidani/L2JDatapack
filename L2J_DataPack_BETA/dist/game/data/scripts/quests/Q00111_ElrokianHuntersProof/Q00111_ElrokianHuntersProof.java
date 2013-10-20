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
package quests.Q00111_ElrokianHuntersProof;

import java.util.HashMap;
import java.util.Map;

import com.l2jserver.gameserver.enums.QuestSound;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.holders.QuestItemHolder;
import com.l2jserver.gameserver.model.quest.Quest;
import com.l2jserver.gameserver.model.quest.QuestState;
import com.l2jserver.gameserver.model.quest.State;
import com.l2jserver.gameserver.util.Util;

/**
 * Elrokian Hunter's Proof (111)
 * @author Adry_85
 */
public class Q00111_ElrokianHuntersProof extends Quest
{
	// NPCs
	private static final int MARQUEZ = 32113;
	private static final int MUSHIKA = 32114;
	private static final int ASAMAH = 32115;
	private static final int KIRIKACHIN = 32116;
	// Items
	private static final int ELROKIAN_TRAP = 8763;
	private static final int TRAP_STONE = 8764;
	private static final int DIARY_FRAGMENT = 8768;
	private static final int EXPEDITION_MEMBERS_LETTER = 8769;
	private static final int ORNITHOMINUS_CLAW = 8770;
	private static final int DEINONYCHUS_BONE = 8771;
	private static final int PACHYCEPHALOSAURUS_SKIN = 8772;
	private static final int PRACTICE_ELROKIAN_TRAP = 8773;
	// Misc
	private static final int MIN_LEVEL = 75;
	// Mobs
	private static final Map<Integer, QuestItemHolder> MOBS_DROP_CHANCES = new HashMap<>();
	static
	{
		MOBS_DROP_CHANCES.put(22196, new QuestItemHolder(DIARY_FRAGMENT, 510, 4)); // velociraptor_leader
		MOBS_DROP_CHANCES.put(22197, new QuestItemHolder(DIARY_FRAGMENT, 510, 4)); // velociraptor
		MOBS_DROP_CHANCES.put(22198, new QuestItemHolder(DIARY_FRAGMENT, 510, 4)); // velociraptor_s
		MOBS_DROP_CHANCES.put(22218, new QuestItemHolder(DIARY_FRAGMENT, 250, 4)); // velociraptor_n
		MOBS_DROP_CHANCES.put(22223, new QuestItemHolder(DIARY_FRAGMENT, 260, 4)); // velociraptor_leader2
		MOBS_DROP_CHANCES.put(22200, new QuestItemHolder(ORNITHOMINUS_CLAW, 660, 11)); // ornithomimus_leader
		MOBS_DROP_CHANCES.put(22201, new QuestItemHolder(ORNITHOMINUS_CLAW, 330, 11)); // ornithomimus
		MOBS_DROP_CHANCES.put(22202, new QuestItemHolder(ORNITHOMINUS_CLAW, 660, 11)); // ornithomimus_s
		MOBS_DROP_CHANCES.put(22219, new QuestItemHolder(ORNITHOMINUS_CLAW, 330, 11)); // ornithomimus_n
		MOBS_DROP_CHANCES.put(22224, new QuestItemHolder(ORNITHOMINUS_CLAW, 330, 11)); // ornithomimus_leader2
		MOBS_DROP_CHANCES.put(22203, new QuestItemHolder(DEINONYCHUS_BONE, 650, 11)); // deinonychus_leader
		MOBS_DROP_CHANCES.put(22204, new QuestItemHolder(DEINONYCHUS_BONE, 320, 11)); // deinonychus
		MOBS_DROP_CHANCES.put(22205, new QuestItemHolder(DEINONYCHUS_BONE, 660, 11)); // deinonychus_s
		MOBS_DROP_CHANCES.put(22220, new QuestItemHolder(DEINONYCHUS_BONE, 320, 11)); // deinonychus_n
		MOBS_DROP_CHANCES.put(22225, new QuestItemHolder(DEINONYCHUS_BONE, 320, 11)); // deinonychus_leader2
		MOBS_DROP_CHANCES.put(22208, new QuestItemHolder(PACHYCEPHALOSAURUS_SKIN, 500, 11)); // pachycephalosaurus_ldr
		MOBS_DROP_CHANCES.put(22209, new QuestItemHolder(PACHYCEPHALOSAURUS_SKIN, 500, 11)); // pachycephalosaurus
		MOBS_DROP_CHANCES.put(22210, new QuestItemHolder(PACHYCEPHALOSAURUS_SKIN, 500, 11)); // pachycephalosaurus_s
		MOBS_DROP_CHANCES.put(22221, new QuestItemHolder(PACHYCEPHALOSAURUS_SKIN, 490, 11)); // pachycephalosaurus_n
		MOBS_DROP_CHANCES.put(22226, new QuestItemHolder(PACHYCEPHALOSAURUS_SKIN, 500, 11)); // pachycephalosaurus_ldr2
	}
	
	private Q00111_ElrokianHuntersProof(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addKillId(MOBS_DROP_CHANCES.keySet());
		addStartNpc(MARQUEZ);
		addTalkId(MARQUEZ, MUSHIKA, ASAMAH, KIRIKACHIN);
		registerQuestItems(DIARY_FRAGMENT, EXPEDITION_MEMBERS_LETTER, ORNITHOMINUS_CLAW, DEINONYCHUS_BONE, PACHYCEPHALOSAURUS_SKIN, PRACTICE_ELROKIAN_TRAP);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "32113-02.htm":
			case "32113-05.htm":
			case "32113-04.html":
			case "32113-10.html":
			case "32113-11.html":
			case "32113-12.html":
			case "32113-13.html":
			case "32113-14.html":
			case "32113-18.html":
			case "32113-19.html":
			case "32113-20.html":
			case "32113-21.html":
			case "32113-22.html":
			case "32113-23.html":
			case "32113-24.html":
			case "32115-08.html":
			case "32116-03.html":
			{
				htmltext = event;
				break;
			}
			case "32113-03.html":
			{
				st.startQuest();
				st.setMemoState(1);
				htmltext = event;
				break;
			}
			case "32113-15.html":
			{
				if (st.isMemoState(3))
				{
					st.setMemoState(4);
					st.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "32113-25.html":
			{
				if (st.isMemoState(5))
				{
					st.setMemoState(6);
					st.setCond(6, true);
					st.giveItems(EXPEDITION_MEMBERS_LETTER, 1);
					htmltext = event;
				}
				break;
			}
			case "32115-03.html":
			{
				if (st.isMemoState(2))
				{
					st.setMemoState(3);
					st.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "32115-06.html":
			{
				if (st.isMemoState(9))
				{
					st.setMemoState(10);
					st.setCond(9);
					st.playSound(QuestSound.ETCSOUND_ELROKI_SONG_FULL);
					htmltext = event;
				}
				break;
			}
			case "32115-09.html":
			{
				if (st.isMemoState(10))
				{
					st.setMemoState(11);
					st.setCond(10, true);
					htmltext = event;
				}
				break;
			}
			case "32116-04.html":
			{
				if (st.isMemoState(7))
				{
					st.setMemoState(8);
					st.playSound(QuestSound.ETCSOUND_ELROKI_SONG_FULL);
					htmltext = event;
				}
				break;
			}
			case "32116-07.html":
			{
				if (st.isMemoState(8))
				{
					st.setMemoState(9);
					st.setCond(8, true);
					htmltext = event;
				}
				break;
			}
			case "32116-10.html":
			{
				if (st.isMemoState(12) && st.hasQuestItems(PRACTICE_ELROKIAN_TRAP))
				{
					st.takeItems(PRACTICE_ELROKIAN_TRAP, -1);
					st.giveItems(ELROKIAN_TRAP, 1);
					st.giveItems(TRAP_STONE, 100);
					st.giveAdena(1071691, true);
					st.addExpAndSp(553524, 55538);
					st.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(player, -1, 3, npc);
		if ((qs != null) && qs.isStarted() && Util.checkIfInRange(1500, npc, player, false))
		{
			final QuestItemHolder item = MOBS_DROP_CHANCES.get(npc.getId());
			if ((item.getCount() == qs.getMemoState()))
			{
				if (qs.isCond(4))
				{
					giveItemRandomly(player, npc, item.getId(), 1, 50, item.getChance(), true);
					if (qs.getQuestItemsCount(DIARY_FRAGMENT) >= 50)
					{
						qs.setCond(5);
					}
				}
				else if (qs.isCond(10))
				{
					giveItemRandomly(player, npc, item.getId(), 1, 10, item.getChance(), true);
					if ((qs.getQuestItemsCount(ORNITHOMINUS_CLAW) >= 10) && (qs.getQuestItemsCount(DEINONYCHUS_BONE) >= 10) && (qs.getQuestItemsCount(PACHYCEPHALOSAURUS_SKIN) >= 10))
					{
						qs.setCond(11);
					}
				}
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		String htmltext = getNoQuestMsg(player);
		if (st == null)
		{
			return htmltext;
		}
		
		switch (st.getState())
		{
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
			{
				if (npc.getId() == MARQUEZ)
				{
					htmltext = (player.getLevel() >= MIN_LEVEL) ? "32113-01.htm" : "32113-06.html";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case MARQUEZ:
					{
						switch (st.getMemoState())
						{
							case 1:
							{
								htmltext = "32113-07.html";
								break;
							}
							case 2:
							{
								htmltext = "32113-08.html";
								break;
							}
							case 3:
							{
								htmltext = "32113-09.html";
								break;
							}
							case 4:
							{
								if (st.getQuestItemsCount(DIARY_FRAGMENT) < 50)
								{
									htmltext = "32113-16.html";
								}
								else
								{
									st.takeItems(DIARY_FRAGMENT, -1);
									htmltext = "32113-17.html";
									st.setMemoState(5);
								}
								break;
							}
							case 5:
							{
								htmltext = "32113-26.html";
								break;
							}
							case 6:
							{
								htmltext = "32113-27.html";
								break;
							}
							case 7:
							case 8:
							{
								htmltext = "32113-28.html";
								break;
							}
							case 9:
							{
								htmltext = "32113-29.html";
								break;
							}
							case 10:
							case 11:
							case 12:
							{
								htmltext = "32113-30.html";
								break;
							}
						}
						break;
					}
					case MUSHIKA:
					{
						if (st.isMemoState(1))
						{
							st.setCond(2, true);
							st.setMemoState(2);
							htmltext = "32114-01.html";
						}
						else if ((st.getMemoState() > 1) && (st.getMemoState() < 10))
						{
							htmltext = "32114-02.html";
						}
						else
						{
							htmltext = "32114-03.html";
						}
						break;
					}
					case ASAMAH:
					{
						switch (st.getMemoState())
						{
							case 1:
							{
								htmltext = "32115-01.html";
								break;
							}
							case 2:
							{
								htmltext = "32115-02.html";
								break;
							}
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:
							case 8:
							{
								htmltext = "32115-04.html";
								break;
							}
							case 9:
							{
								htmltext = "32115-05.html";
								break;
							}
							case 10:
							{
								htmltext = "32115-07.html";
								break;
							}
							case 11:
							{
								if ((st.getQuestItemsCount(ORNITHOMINUS_CLAW) < 10) || (st.getQuestItemsCount(DEINONYCHUS_BONE) < 10) || (st.getQuestItemsCount(PACHYCEPHALOSAURUS_SKIN) < 10))
								{
									htmltext = "32115-10.html";
								}
								else if (((st.getQuestItemsCount(ORNITHOMINUS_CLAW) >= 10) && (st.getQuestItemsCount(DEINONYCHUS_BONE) >= 10) && (st.getQuestItemsCount(PACHYCEPHALOSAURUS_SKIN) >= 10)))
								{
									st.setMemoState(12);
									st.setCond(12, true);
									st.giveItems(PRACTICE_ELROKIAN_TRAP, 1);
									st.takeItems(ORNITHOMINUS_CLAW, -1);
									st.takeItems(DEINONYCHUS_BONE, -1);
									st.takeItems(PACHYCEPHALOSAURUS_SKIN, -1);
									htmltext = "32115-11.html";
								}
								break;
							}
							case 12:
							{
								htmltext = "32115-12.html";
								break;
							}
						}
						break;
					}
					case KIRIKACHIN:
					{
						switch (st.getMemoState())
						{
							case 1:
							case 2:
							case 3:
							case 4:
							case 5:
							{
								htmltext = "32116-01.html";
								break;
							}
							case 6:
							{
								if (st.hasQuestItems(EXPEDITION_MEMBERS_LETTER))
								{
									st.setMemoState(7);
									st.setCond(7, true);
									st.takeItems(EXPEDITION_MEMBERS_LETTER, -1);
									htmltext = "32116-02.html";
								}
								break;
							}
							case 7:
							{
								htmltext = "32116-05.html";
								break;
							}
							case 8:
							{
								htmltext = "32116-06.html";
								break;
							}
							case 9:
							case 10:
							case 11:
							{
								htmltext = "32116-08.html";
								break;
							}
							case 12:
							{
								htmltext = "32116-09.html";
								break;
							}
						}
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	public static void main(String args[])
	{
		new Q00111_ElrokianHuntersProof(111, Q00111_ElrokianHuntersProof.class.getSimpleName(), "Elrokian Hunter's Proof");
	}
}
