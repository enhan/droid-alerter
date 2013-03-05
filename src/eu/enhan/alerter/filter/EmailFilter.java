package eu.enhan.alerter.filter;

import eu.enhan.alerter.common.AlertMessage;
import eu.enhan.alerter.common.Email;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 05/03/13
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public interface EmailFilter {

	AlertMessage filter(Email email);

}
