package ru.seniorjava.protei.kmb.server;

import ru.seniorjava.protei.kmb.client.StringService;
import ru.seniorjava.protei.kmb.client.objects.Bearings;
import ru.seniorjava.protei.kmb.client.objects.Boot;
import ru.seniorjava.protei.kmb.client.objects.Frame;
import ru.seniorjava.protei.kmb.client.objects.RollerSkates;
import ru.seniorjava.protei.kmb.client.objects.Wheels;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class StringServiceImpl extends RemoteServiceServlet implements StringService {

	@Override
	public Integer checkString(RollerSkates rollerSkates) throws IllegalArgumentException {
		Boot bt = rollerSkates.getBoot();
		Frame fr = rollerSkates.getFrame();
		Wheels wh = rollerSkates.getWheels();
		Bearings br = rollerSkates.getBearings();
		 return bt.getName().concat(bt.getMaterial()).concat(bt.getMounting()).concat(bt.getCuff())
		.concat(fr.getName()).concat(fr.getMaterial()).concat(fr.getMounting()).concat(fr.getLength()).concat(fr.getSetup())
		.concat(wh.getName()).concat(wh.getSize()).concat(wh.getHardness())
		.concat(br.getName()).concat(br.getMaterial()).concat(br.getBalls()).concat(br.getMaterialBalls()).concat(br.getClassification()).length();
	}

}
