package ru.seniorjava.protei.kmb.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.seniorjava.protei.kmb.client.objects.RollerSkates;

public interface StringServiceAsync {

	void checkString(RollerSkates rollerSkates, AsyncCallback<Integer> callback);

}
