package com.personal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.bean.Name;
import com.personal.bean.PersonV1;
import com.personal.bean.PersonV2;

/*
 * Versioning RESTful Services
 * 
 * ---------------------------
 * Basic Approach with URIs - URI Versioning
 * 
 * Mapping different URIs is the
 * 	basic way of achieving versioning
 * 
 * ex: @GetMapping("/V1/person")
 * 	   @GetMapping("/V2/person")
 * ----------------------------
 * 
 * Use of a Request Parameter
 * 
 * ex: @GetMapping(value="/person/param", params="version=2")
 * ----------------------------
 * 
 * Use of a Header Parameter
 * 
 * ex: @GetMapping(value="/person/header", headers="X-API-VERSION=2")
 * 
 * ----------------------------
 * 
 * By using Produces (Content Negotiation/Accept Versioning/Mime Versioning)
 * 
 * ex: @GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json")
 * 
 * ----------------------------
 */
@RestController
public class PersonVersioningController {
	
	// ==================== URI Versioning ====================
	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Scarlett", "Johanson"));
	}
	
	// ==================== Params ====================
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Scarlett", "Johanson"));
	}
	
	// ==================== Headers ====================
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Scarlett", "Johanson"));
	}
	
	// ==================== Produces ====================
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Scarlett", "Johanson"));
	}
}
