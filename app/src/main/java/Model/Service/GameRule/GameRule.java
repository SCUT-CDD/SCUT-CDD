package Model.Service.GameRule;

import Model.Entity.SelectedCardGroup;

public interface GameRule {
    public boolean validate(SelectedCardGroup scg);
}
