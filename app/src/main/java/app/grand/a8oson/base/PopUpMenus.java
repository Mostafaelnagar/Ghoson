package app.grand.a8oson.base;

import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;

import java.util.List;


public class PopUpMenus {

    public static PopupMenu showWorkingHoursPopUp(Context context, View view, List<String> types) {
        PopupMenu typesPopUps;
        typesPopUps = new PopupMenu(context, view);
        for (int i = 0; i < types.size(); i++) {
            typesPopUps.getMenu().add(types.get(i));
        }
        typesPopUps.show();
        return typesPopUps;
    }

    public static PopupMenu showOrderStatusPopUp(Context context, View view, List<PopUpItem> types) {
        PopupMenu typesPopUps;
        typesPopUps = new PopupMenu(context, view);
        for (int i = 0; i < types.size(); i++) {
            typesPopUps.getMenu().add(i, i, i, types.get(i).name);
        }
        typesPopUps.show();
        return typesPopUps;
    }


}
