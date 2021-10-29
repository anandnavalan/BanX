package com.tis.in.BanX.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tis.in.BanX.domain.PlotQuestion;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.PlotQuestionService;

@RestController
public class PlotQuestionController {

	@Autowired
	PlotQuestionService plotQuestionService;

	@RequestMapping(name = "/createplotquestion", value = "createPlotQuestion", method = RequestMethod.POST)
	private ResponseEntity<Object> createPlotQuestion(@RequestBody @Valid PlotQuestion plotQuestion) {
		Optional<PlotQuestion> optionalPlotQuestion = plotQuestionService
				.getPlotQuestion(plotQuestion.getPlotQuestionId());
		if (optionalPlotQuestion.isPresent()) {
			return ResponseHandler.generateResponse("PlotQuestion already existing.", HttpStatus.CONFLICT);

		} else {
			plotQuestion = plotQuestionService.addOrUpdatePlotQuestion(plotQuestion);
			return ResponseHandler.generateResponse("PlotQuestion Created Successfully", HttpStatus.CREATED,
					plotQuestion);

		}
	}

	@RequestMapping(value = "/updateplotquestion", name = "updatePlotQuestion", method = RequestMethod.PUT)
	private ResponseEntity<Object> updatePlotQuestion(@RequestBody @Valid PlotQuestion plotQuestion) {
		Optional<PlotQuestion> optionalPlotQuestion = plotQuestionService
				.getPlotQuestion(plotQuestion.getPlotQuestionId());
		if (optionalPlotQuestion.isPresent()) {
			return ResponseHandler.generateResponse("PlotQuestion not exists in your system", HttpStatus.NOT_FOUND);

		} else {
			plotQuestion = plotQuestionService.addOrUpdatePlotQuestion(plotQuestion);
			return ResponseHandler.generateResponse("PlotQuestion Updated Successfully", HttpStatus.OK, plotQuestion);
		}
	}

	@RequestMapping(value = "/getplotquestion", name = "getPlotQuestions", method = RequestMethod.GET)
	public ResponseEntity<Object> getSPlotQuestion() {
		List<PlotQuestion> plotQuestions = plotQuestionService.getAllPlotQuestion();
		return ResponseHandler.generateResponse("PlotQuestion Retrieved Successfully", HttpStatus.OK, plotQuestions);
	}

	@GetMapping(value = "/getplotquestion/{id}", name = "getidPlotQuestion")
	public ResponseEntity<Object> getidPlotQuestionbyid(@PathVariable long id) {
		Optional<PlotQuestion> plotQuestion = plotQuestionService.getPlotQuestion(id);
		return ResponseHandler.generateResponse("PlotQuestion Details Retrieved Successfully", HttpStatus.OK,
				plotQuestion.get());
	}

}
