package eu.enhan.alerter.filter;

import eu.enhan.alerter.common.AlertMessage;
import eu.enhan.alerter.common.Email;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 05/03/13
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public interface EmailFilter {

    @Deprecated
	AlertMessage filter(Email email);

    Set<Runnable> createActions(Email email);

}
