package org.parabot.scriptwriter.revival.aCooker.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.scriptwriter.revival.aCooker.revival_lib.Constants;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

import static org.rev317.min.api.methods.Walking.walkTo;


public class Cooking implements Strategy {

    @Override
    public boolean activate() {
        return Players.getMyPlayer().getAnimation() != Constants.COOKING_ANIMATION_ID
                && Inventory.getCount(Constants.RAW_KARAMBWAN_ID) > 0;
    }

    @Override
    public void execute() {
        SceneObject fire = SceneObjects.getClosest(Constants.FIRE_ID);
        final Tile cookingSpot = new Tile(fire.getLocation().getX() - 1,fire.getLocation().getY());
        if(Players.getMyPlayer().getLocation().equals(cookingSpot)) {
            if(Interfaces.getBackDialogId() != Constants.COOK_KARAMBWAN_DIALOG_ID) {
                Inventory.getItem(Constants.RAW_KARAMBWAN_ID).interact(Items.Option.USE);
                Time.sleep(500);
                Menu.sendAction(62, fire.getHash(), fire.getLocalRegionX(), fire.getLocalRegionY());
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Interfaces.getBackDialogId() == Constants.COOK_KARAMBWAN_DIALOG_ID;
                    }
                }, 2000);
            }
            if(Interfaces.getBackDialogId() == Constants.COOK_KARAMBWAN_DIALOG_ID) {
                Menu.clickButton(13717);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Players.getMyPlayer().getAnimation() == Constants.COOKING_ANIMATION_ID;
                    }
                }, 3000);
            }
        } else {
            walkTo(cookingSpot);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Players.getMyPlayer().getLocation().equals(cookingSpot);
                }
            },4000);
        }
    }
}
