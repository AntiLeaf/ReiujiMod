package ReiujiMod.variable;

import ReiujiMod.abstracts.AbstractReiujiCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class TempHPVariable extends DynamicVariable {
		@Override
		public String key() {
			return "TempHP";
		}

		@Override
		public boolean isModified(AbstractCard c) {
			if (!(c instanceof AbstractReiujiCard))
				return false;

			AbstractReiujiCard card = (AbstractReiujiCard) c;
			return card.tempHP == card.baseTempHP;
		}

		@Override
		public void setIsModified(AbstractCard card, boolean v) {
			// Do something such that isModified will return the value v.
			// This method is only necessary if you want smith upgrade previews to function correctly.
		}

		@Override
		public int value(AbstractCard card) {
			return (card instanceof AbstractReiujiCard) ?
					((AbstractReiujiCard) card).tempHP : 0;
		}

		@Override
		public int baseValue(AbstractCard card) {
			return (card instanceof AbstractReiujiCard) ?
					((AbstractReiujiCard) card).baseTempHP : 0;
		}

		@Override
		public boolean upgraded(AbstractCard card) {
			return this.isModified(card);
		}
}
