package fiu.kdrg.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import fiu.kdrg.storyline.event.Event;
import fiu.kdrg.storyline.event.NamedEntity;
import fiu.kdrg.storyline.event.RawEvent;

public class EventUtil {

	
	public final static String YEAR_REGEX = "\\s+?[0-9]{4}\\s?";
	
	public final static String MONTH_REGEX = "[Jj]an(uary)?|[Ff]eb(ruary)?|[Mm]ar(ch)?|[Aa]pr(il)?|[Mm]ay|[Jj]un(e)?|[Jj]ul(y)?|" +
			"[Aa]ug(ust)?|[Ss]ep(tember)?|[Oo]ct(ober)?|[Nn]ov(ember)?|[Dd]ec(ember)?";
	
	public final static String DAY_REGEX = "\\s+?(0?[1-9]|[1-2][0-9]|3[0-1])(st|nd|rd|th)?\\s+?";
	
	
	
	
	
	public static void displayEvent(Event event)
	{
		if(null == event)
			return;
		System.out.println(event.getEventURL());
		System.out.println(event.getEventContent());
		System.out.println("Location: " + event.getEventLocation() +"\t Date: " + event.getEventDate());
		if(null != event.getLatlng())
			System.out.println("Latlng: " + event.getLatlng().getLatitude() + "," + event.getLatlng().getLongtitude());
	}
	
	
	
	
	/**
	 * display all elements of events and content of every element
	 * @param events
	 */
	public static void displayEvents(List<Event> events)
	{
		if(null == events || events.isEmpty())
			return;
		
		Event tmp;
		int i = 1;
		for(Iterator<Event> it = events.iterator(); it.hasNext(); )
		{
			tmp = it.next();
			System.out.println(i++);
			displayEvent(tmp);
		}
	}
	
	
	
	/**
	 * display a single rawEvent
	 * @param rawEvent
	 */
	public static void displayRawEvent(RawEvent rawEvent)
	{
		if(null == rawEvent)
			return;
		
		System.out.println(rawEvent.getSentence());
		
		Iterator<NamedEntity> iter = rawEvent.getEntities().iterator();
		while(iter.hasNext())
		{
			NamedEntity tmp = (NamedEntity) iter.next();
			
			System.out.println("TYPE: " + tmp.getType() + "\t" +
								"ENTITYTEXT: " + tmp.getEntityText() + "\t" + 
								"POSITION: " + tmp.getBeginPosition() + "\t");
			
		}
		
	}
	
	
	
	/**
	 * display a list of rawevents
	 * @param rawEvents
	 */
	public static void displayRawEvents(List<RawEvent> rawEvents)
	{
		if(rawEvents.isEmpty())
			return;
		
		for(int i = 0; i < rawEvents.size(); i++)
		{
			System.out.println(i+1);
			displayRawEvent(rawEvents.get(i));
		}
	}
	
	
	public static ArrayList<Event> sortEventByDate(ArrayList<Event> events){
		
		Collections.sort(events, new Comparator<Event>() {
			
			@Override
			public int compare(Event o1, Event o2) {
				// TODO Auto-generated method stub
				return o1.getEventDate().compareTo(o2.getEventDate());
			}
			
		});
		return events;
	}
	
	
	public static boolean allAttributesNonempty(Event event)
	{
		boolean hasURL = !(event.getEventURL().equals("") || null == event.getEventURL());
		boolean hasContent = !(event.getEventContent().equals("") || null == event.getEventContent());
		boolean hasLocation = !(event.getEventLocation().equals("") || null == event.getEventLocation());
		boolean hasDate = !( null == event.getEventDate());
		boolean haslatlng = !(null == event.getLatlng());
		
		return (hasURL && hasContent && hasLocation && hasDate && haslatlng);
	}
	
	
	
	
	
	
}