package com.scaffold;

import com.scaffold.action.ActionGenerator;
import com.scaffold.dao.DaoGenerator;
import com.scaffold.jsp.JspForCreateGenerator;
import com.scaffold.jsp.JspForListGenerator;
import com.scaffold.jsp.JspForViewGenerator;
import com.scaffold.service.ServiceGenerator;

public class Scaffold {

	public static void main(String[] args) {

		try {
			String moduleName = "commodity";
			
			//new EntityGenerator().gen(moduleName);
			new DaoGenerator().gen(moduleName);
			new ActionGenerator().gen(moduleName);
			new ServiceGenerator().gen(moduleName);
			new JspForCreateGenerator().gen(moduleName);
			new JspForListGenerator().gen(moduleName);
			new JspForViewGenerator().gen(moduleName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
 

}
