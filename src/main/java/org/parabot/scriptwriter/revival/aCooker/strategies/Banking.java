package org.parabot.scriptwriter.revival.aCooker.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.scriptwriter.revival.aCooker.revival_lib.Constants;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;

public class Banking implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.getCount(Constants.RAW_KARAMBWAN_ID) == 0;
    }

    @Override
    public void execute() {
        if(Bank.isOpen()) {
            if(Inventory.getCount(Constants.RAW_KARAMBWAN_ID) == 0) {
                Menu.clickButton(Constants.DEPOSIT_ALL_ID);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Inventory.isEmpty();
                    }
                },2000);
            }
            if(Inventory.isEmpty()) {
                Menu.clickButton(5387);
                Time.sleep(1000);
                Bank.withdraw(Constants.RAW_KARAMBWAN_ID, 27, 1000);
                Time.sleep(3000);
                if(Inventory.getCount(Constants.RAW_KARAMBWAN_ID) == 27) {
                    Bank.close();
                }
            }
        } else {
            SceneObject bank = SceneObjects.getClosest(Constants.BANK_ID);
            Bank.open(bank);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Bank.isOpen();
                }
            }, 4000);
        }
    }

}
