package eu.enhan.alerter.filter;

import com.google.common.collect.ImmutableSet;
import eu.enhan.alerter.common.Email;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 06/03/13
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
class AllMatchesFilter extends Filter{

	AllMatchesFilter(ActionTemplate actionTemplate) {
		super(ImmutableSet.<FilterRule>of(), actionTemplate);
	}

	@Override
	public boolean matches(Email email) {
		return true;
	}
}
