package eu.enhan.alerter.filter;

import eu.enhan.alerter.common.AlertMessage;
import eu.enhan.alerter.common.Email;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 05/03/13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public class BasicOnSubjectFilter implements EmailFilter {
	@Override
	public AlertMessage filter(Email email) {
		AlertMessage msg = new AlertMessage();
		if (email.getSubject().contains("sévère")) {
			msg.setAlertLevel("Alerte rouge.");
		} else if (email.getSubject().contains("moyen")) {
			msg.setAlertLevel("Alerte jaune.");
		} else {
			msg.setAlertLevel("Alerte bleue.");
		}
		return msg;

	}
}
