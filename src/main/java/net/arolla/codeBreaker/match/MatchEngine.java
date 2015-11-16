package net.arolla.codeBreaker.match;

import java.util.List;

import net.arolla.codeBreaker.match.Match.MatchType;
import net.arolla.codeBreaker.response.ResponseFormatter;

/**
 * @author Emmanuel
 *
 */
public class MatchEngine {

	private final MatchBuilder builder;
	private final List<Match> secrete;
	private final MatchFilter filter;

	public MatchEngine(String secreteCode) {
		this.builder = new MatchBuilder();
		this.secrete = builder.getWordMatch(secreteCode);
		this.filter = new MatchFilter(secrete);
	}

	public ResponseFormatter getResponseFormatter(String guess) {
		filter.set(builder.getWordMatch(guess)).filterAccordingType(MatchType.EXACT)
		                                       .filterAccordingType(MatchType.DIGIT);
		return new ResponseFormatter(filter.getResults(), secrete.size());
	}
}