package eu.enhan.alerter.filter;

import android.speech.tts.TextToSpeech;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import eu.enhan.alerter.action.ActionFactory;
import eu.enhan.alerter.common.AlertMessage;
import eu.enhan.alerter.common.Email;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A prioritized filter
 *
 * @author Emmanuel Nhan
 */
public class CompositeFilter implements EmailFilter{

	private final ActionFactory factory;
	private final List<Filter> filters;

	// TODO Create a real implementation
	public CompositeFilter(TextToSpeech tts){
		factory = new ActionFactory(tts);

		// Filters
		Filter red = Filter.newBuilder().
				withOperator(Filter.CombinationOperator.OR).
				withRule(new Rule(Field.SUBJECT, Operator.STARTS_WITH, "Important")).
				withRule(new Rule(Field.SUBJECT, Operator.STARTS_WITH, "Sévère")).
				withActionTemplate(ActionTemplate.newBuilder().withSpeak(true).withTextToSpeakPattern("Alerte rouge. Problème grave rencontré.").build()).
				build();

		Filter yellow = Filter.newBuilder().
				withRule(new Rule(Field.SUBJECT, Operator.CONTENTS, "moyen")).
				withActionTemplate(ActionTemplate.newBuilder().withSpeak(true).withTextToSpeakPattern("Alerte jaune. Problème important rencontré.").build())
				.build();

		Filter blue = Filter.newBuilder().withOperator(Filter.CombinationOperator.ALWAYS_TRUE).
				withActionTemplate(ActionTemplate.newBuilder().withSpeak(true).withTextToSpeakPattern("Alerte technique.").build())
				.build();

		filters = ImmutableList.of(red, yellow, blue);

	}


	@Override
	public AlertMessage filter(Email email) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public Set<Runnable> createActions(Email email) {
		Iterator<Filter> it = filters.iterator();
		Filter found = null;
		while (it.hasNext() && found == null){
			Filter f = it.next();
			if (f.matches(email)){
				found = f;
			}
		}

		if (found != null){
			return found.createActions(factory,email);
		}else{
			return ImmutableSet.of();
		}

	}
}
