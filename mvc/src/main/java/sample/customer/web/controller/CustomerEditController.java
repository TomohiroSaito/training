package sample.customer.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import sample.customer.biz.domain.Customer;
import sample.customer.biz.service.CustomerService;

@Controller
@RequestMapping("/customer/{customerId}")
@SessionAttributes("editCustomer")
public class CustomerEditController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/edit", method = GET)
	public String redirectToEntryForm(
			@PathVariable int customerId, Model mode.)
								throws DataNotFoundException {
		Customer customer = customerService.findById(customerId);
		model.addAttribute("editCustomer", customer);

		return "redirect:enter";
	}

	@RequestMapping(value = "/enter", method = GET)
	public String showEntryForm() {
		return "customer/edit/enter";
	}

	@RequestMapping(
			value = "/enter", params = "_event_proceed", method = POST)
	public String verify(
			@Valid @ModelAttribute("editCustomer") Customer customer,
			Errors errors) {
		if (errors.hasErrors()) {
			return "customer/edit/enter";
		}
		return "redirect:review";
	}

	@RequestMapping(value = "/review", method = GET)
	public String showReview() {
		return "customer/edit/review";
	}

	@RequestMapping(
			value = "/review", params = "_event_confirmed", method = POST)
	public String edit(
			@ModelAttribute("editCustomer") Customer customer)
									throws DataNotFoundException {
		customerService.update(customer);

		return "redirect:edited";
	}

	@RequestMapping(value = "/edited", method = GET)
	public String showEdited(
			SessionStatus sessionStatus) {
		sessionStatus.setComplete();

		return "customer/edit/edited";
	}
}
