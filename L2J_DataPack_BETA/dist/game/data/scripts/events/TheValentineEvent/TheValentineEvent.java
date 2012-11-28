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
package events.TheValentineEvent;

import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.event.LongTimeEvent;
import com.l2jserver.gameserver.model.quest.QuestState;
import com.l2jserver.gameserver.model.quest.State;

/**
 * The Valentine Event event AI.
 * @author Gnacik
 */
public class TheValentineEvent extends LongTimeEvent
{
	private static final int _npc = 4301;
	private static final int _recipe = 20191;

	public TheValentineEvent(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(_npc);
		addFirstTalkId(_npc);
		addTalkId(_npc);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			return getNoQuestMsg(player);
		}
		
		String htmltext = event;
		if (event.equalsIgnoreCase("4301-3.htm"))
		{
			if (st.isCompleted())
			{
				htmltext = "4301-4.htm";
			}
			else
			{
				st.giveItems(_recipe, 1);
				st.playSound(QuestSound.ITEMSOUND_QUEST_ITEMGET);
				st.setState(State.COMPLETED);
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		if (player.getQuestState(getName()) == null)
		{
			newQuestState(player);
		}
		return npc.getNpcId() + ".htm";
	}
	
	public static void main(String[] args)
	{
		new TheValentineEvent(-1, "TheValentineEvent", "events");
	}
}
