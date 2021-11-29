package com.revature.projectOneFinal.main;

import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import com.revature.projectOneFinal.dao.ReimbursemnetDAO;
import com.revature.projectOneFinal.dao.UserDAO;
import com.revature.projectOneFinal.model.ReimbursementModel;
import com.revature.projectOneFinal.model.UserModel;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			 config.addStaticFiles("D:\\Eclipse WS\\projectOneFinal\\src\\main\\resources\\public", Location.EXTERNAL);
		 }).start(7000);
		

		app.post("/login", ctx -> {
			UserModel user = new UserModel();
			String username = ctx.formParam("username");
			user = UserDAO.findById(username);
			if (Objects.isNull(user)) {
				ctx.html("Please enter a valid username"); // When employee/manager userId wrong
			} else {
				String password = ctx.formParam("password");
				System.out.println(user.toString());
				if (user.isPasswordMatch(password)) {
					if (user.getRole().equalsIgnoreCase("manager")) {
						ctx.redirect("managerView.html"); // When user is manager
						
					} else {
						ctx.redirect("employeeView.html"); // when user is employee
					}
				} else {

					ctx.html("Wrong Password");   // In case of wrong password
				}
				
			}
			
//			ctx.html("ok");

		});
		
		
		
		
		List<ReimbursementModel> pendingReimbursements = ReimbursemnetDAO.findByPendingStatus();
		
		app.get("/reimbursements", ctx -> ctx.jsonStream(pendingReimbursements));
		
		List<ReimbursementModel> reimbursements = ReimbursemnetDAO.findAll();
		app.get("/reimbursement", ctx -> ctx.jsonStream(reimbursements));
		
		List<ReimbursementModel> ps = ReimbursemnetDAO.findAll();
		System.out.println(ps.toString());
		app.get("/esp", ctx -> ctx.jsonStream(ps));
		
		app.post("/reimbursementForm", ctx -> {

			String reason = ctx.formParam("reason");
			int amount =  Integer.parseInt(ctx.formParam("amount"));
			String status ="pending";
			

			ReimbursementModel rs = new ReimbursementModel(reason, amount,status);
			ReimbursemnetDAO.save(rs);
			ctx.html("done");
		});
	}

}

