package com.google.android.systemui;

import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.VendorServices;
import com.android.systemui.R;
import com.android.systemui.statusbar.phone.StatusBar;
import com.google.android.systemui.dreamliner.DockObserver;
import com.google.android.systemui.dreamliner.DreamlinerContext;
import com.google.android.systemui.elmyra.ElmyraContext;
import com.google.android.systemui.elmyra.ElmyraService;
import com.google.android.systemui.elmyra.ServiceConfigurationGoogle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GoogleServices extends VendorServices {
    private ArrayList<Object> mServices = new ArrayList();

    private void addService(Object obj) {
        if (obj != null) {
            this.mServices.add(obj);
        }
    }

    public void start() {
        StatusBar statusBar = (StatusBar) SysUiServiceProvider.getComponent(mContext, StatusBar.class);
        if (new ElmyraContext(mContext).isAvailable()) {
            addService(new ElmyraService(mContext, new ServiceConfigurationGoogle(mContext)));
        }
        if (new DreamlinerContext(mContext).isAvailable()) {
            addService(new DockObserver(mContext));
        }
    }
}
