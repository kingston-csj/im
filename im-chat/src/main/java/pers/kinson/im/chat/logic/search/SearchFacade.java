package pers.kinson.im.chat.logic.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchFacade {

	@Autowired
	private SearchService searchService;

}
