package com.promineotech.imdb.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.imdb.models.TitleModel;
import com.promineotech.imdb.services.TitleService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@OpenAPIDefinition(info = @Info(title = "IMDb: The Next Generation"))
@Tag(name="Titles")
public class TitleController {
  private final int MAX_ITEMS_PER_REQUEST = 500;
  private final int MINIMUM_TITLE_RELEASE_YEAR = 1874;
  
  //@Autowired
  private TitleService service;
  
  public TitleController(TitleService service) {
    this.service = service;
  }
  
  @Operation(summary = "Get all titles")
  @RequestMapping(value="/titles", method = RequestMethod.GET)
  public List<TitleModel> all() {
    List<TitleModel> titles = service.all(MAX_ITEMS_PER_REQUEST);
    return titles;
  }
  
  @Operation(summary = "Gets title by unique id")
  @RequestMapping(value="/titles/{id}", method = RequestMethod.GET) 
  public TitleModel get(@PathVariable String id) {
    if (! id.startsWith("tt")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested title id was not supported. Must start with tt");
    }
    
    TitleModel title = service.get(id);
    if (title != null) {
      return title;
    }
    
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested title was not found.");
  }
  
  /**
   * Checks to see if the title is valid.
   * @param title The title to validate
   * @return True if value, otherwise returns false.
   */
  private boolean isValid(TitleModel title) {
    if ((title.getId() == null || title.getId().isEmpty())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title ID is required.");
    }
    if ((title.getName() == null) || (title.getName().isEmpty())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title name is required.");
    }
    if (title.getReleasedYear() < MINIMUM_TITLE_RELEASE_YEAR) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "Title release year must be greater than or equal to " + 
                MINIMUM_TITLE_RELEASE_YEAR);
    }
    return true;
  }
  
  @Operation(summary = "Creates a new title")
  @RequestMapping(value="/titles", method = RequestMethod.POST)
  public TitleModel create(@RequestBody TitleModel title) {
    if (title == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No title data provided.");
    }
    if (isValid(title)) {
    }
    
    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid title data specified.");
  }
}
