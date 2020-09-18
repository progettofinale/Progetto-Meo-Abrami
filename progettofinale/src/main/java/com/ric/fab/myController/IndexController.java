package com.ric.fab.myController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.boot.web.servlet.error.ErrorController;

/**classe che implementa il controller utilizzato per la gestione di errori
 *
 */
@Controller
public class IndexController implements ErrorController {
    private static final String PATH="/error";

    /** rotta in cui verrà restituito un errore in caso di mancata mappatura
     * @return ritorna stringa d' errore
     */
    @Override
    @RequestMapping(PATH)
    @ResponseBody
    public String getErrorPath(){
        return "error: no mapping found";
    }


}
