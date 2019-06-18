package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.human.Human;
import services.HumanService;

/**
 * Контроллер разных типов сортировок
 * @author nikita
 *
 */
@Controller
public class SortedController {
	
	@Autowired 
	HumanService hs;

	
    @GetMapping({"/sort"})
    public String sortIncome(Model model, 
    		@RequestParam(value="size", required=true, defaultValue="10") int size,
    		@RequestParam(value="page", required=true, defaultValue="0") int page
    		) {
    	List<Human> res = new ArrayList();
    	model.addAttribute("result", res);
    	
        return "sortIncome";
    }
    
	
    @PostMapping({"/sort/income-asc"})
    public String sortIncomeAsc(Model model, 
    		@RequestParam(value="size", required=true, defaultValue="10") int size,
    		@RequestParam(value="page", required=true, defaultValue="0") int page
    		) {
    	List<Human> res = hs.findPageSortedByIncomeAsc(size, page);
    	model.addAttribute("result", res);
    	
        return "sortIncome";
    }
    
    @PostMapping({"/sort/income-desc"})
    public String sortIncomeDesc(Model model, 
    		@RequestParam(value="size", required=true, defaultValue="10") int size,
    		@RequestParam(value="page", required=true, defaultValue="0") int page
    		) {
    	List<Human> res = hs.findPageSortedByIncomeDesc(size, page);
    	model.addAttribute("result", res);
    	
        return "sortIncome";
    }
    
    @PostMapping({"/sort/tax-desc"})
    public String sortTaxDesc(Model model, 
    		@RequestParam(value="size", required=true, defaultValue="10") int size,
    		@RequestParam(value="page", required=true, defaultValue="0") int page
    		) {
    	List<Human> res = hs.findPageSortedByTaxDesc(size, page);
    	model.addAttribute("result", res);
    	
        return "sortIncome";
    }
    
    @PostMapping({"/sort/tax-asc"})
    public String sortTaxAsc(Model model, 
    		@RequestParam(value="size", required=true, defaultValue="10") int size,
    		@RequestParam(value="page", required=true, defaultValue="0") int page
    		) {
    	List<Human> res = hs.findPageSortedByTaxAsc(size, page);
    	model.addAttribute("result", res);
    	
        return "sortIncome";
    }


}
