module Trabalho {
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.web;
	requires java.sql;
	requires java.base;
	requires com.jfoenix ;
	
	opens controller;
	opens application;
	opens model;
	opens util;
	
}