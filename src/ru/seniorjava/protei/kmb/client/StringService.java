package ru.seniorjava.protei.kmb.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ru.seniorjava.protei.kmb.client.objects.RollerSkates;

@RemoteServiceRelativePath("check")
public interface StringService extends RemoteService {
	Integer checkString(RollerSkates rollerSkates) throws IllegalArgumentException;
}
