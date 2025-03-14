package dat251_gruppe2.hotelifinder.domain;

import java.util.Comparator;

/**
 * Problem: Hva skal egentlig distance brukes til, og hvordan gjør man det?
 *
 * Eg innså imens eg jobbet at distance blir et litt dårlig grensesnitt.
 * Eg introduserte en ny klasse - Route.
 *
 * Route er et reiseforslag.
 *
 * Distance eksisterer for å kunne sammenligne avstander til clusters,
 * Route eksisterer for å vise reiseforslag på kartet, og representerer
 * strategien på hvordan man kommer seg til reisemålet.
 *
 * Nå har eg extendet comparator, men er usikker på om dette er riktig.
 * Eg vet ikke hva eg driver med, hjelp.
 * - Eivind
 */
public interface Distance extends Comparator<Distance> {

	public void setLocationFrom();

	public Location getLocationFrom();

	public void setLocationTo();

	public Location getLocationTo();

	public Route getRoute();
}
