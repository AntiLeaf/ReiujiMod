//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ReiujiMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import java.util.ArrayList;
import java.util.Arrays;

public class ReiujiAnonymousAction extends AbstractGameAction {
	ArrayList<Runnable> runnables;
	
	public ReiujiAnonymousAction(Runnable... runnables) {
		this.actionType = ActionType.SPECIAL;
		
		this.runnables = new ArrayList<>();
		this.runnables.addAll(Arrays.asList(runnables));
	}
	
	public void update() {
		if (!this.isDone) {
			for (Runnable o : this.runnables)
				if (o != null)
					o.run();
			
			this.isDone = true;
		}
	}
}
