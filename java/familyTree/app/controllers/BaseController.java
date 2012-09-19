package controllers;

import play.Logger;
import play.mvc.Controller;

public class BaseController extends Controller{

    @SuppressWarnings("unused")
	protected static void LOG(String message) {
        Logger.info(message);
    }

}
