package com.swifta.mats.web;

import com.vaadin.navigator.View;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class Initializer {

	private TabSheet m;
	private View crp, cum, ct, cap, cs;

	TabSheet getTS() {
		createTabSheet();
		return m;
	}

	private void createTabSheet() {
		m = new TabSheet();
		// /VerticalLayout u = new VerticalLayout();
		// /u.setId("ds_init");
		// /m.addTab(u, "Dashbord");

		VerticalLayout v = new VerticalLayout();
		// tf = new TextField();
		// v.addComponent(tf);
		v.setId("rp_init");
		m.addTab(v, "MATS Reports");

		// /VerticalLayout um = new VerticalLayout();
		// tf = new TextField();
		// v.addComponent(tf);
		// /um.setId("um_init");
		// /m.addTab(um, "User Management");

		// /VerticalLayout tx = new VerticalLayout();
		// tf = new TextField();
		// v.addComponent(tf);
		// /tx.setId("tx_init");
		// /m.addTab(tx, "Transactions");

		// /VerticalLayout ap = new VerticalLayout();
		// /ap.setId("ap_init");
		// /m.addTab(ap, "Account Profile");
		// /m.getTab(ap).setEnabled(false);

		// /VerticalLayout st = new VerticalLayout();
		// /st.setId("st_init");
		// /m.addTab(st, "Settings");
		// m.getSelectedTab().

		m.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				String id = m.getSelectedTab().getId();
				switch (id) {

				case "rp_init": {
					UI.getCurrent().getNavigator().navigateTo("report");
					break;
				}

				default: {

					if (UI.getCurrent().getSession().getAttribute("user") != null) {
						UI.getCurrent().getNavigator().navigateTo("dashbord");
					} else {
						UI.getCurrent().getNavigator().navigateTo(Login.LOGIN);
					}

					break;
				}
				}

			}

		});

	}
}
