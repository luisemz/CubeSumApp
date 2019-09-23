import { AppService } from "./app.service";
import { Component } from "@angular/core";
import { FormBuilder } from "@angular/forms";

import { ToastrService } from "ngx-toastr";
import { isNumber } from "util";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent {
  dataTest: any;
  testCaseFormGroup: any;
  testDataFormGroup: any;
  testOperationFormGroup: any;

  showDashboard: boolean;
  showInputs: boolean;
  showInputTestCase: boolean;
  showInputTestData: boolean;
  showInputTestOperation: boolean;
  showFinalMessages: boolean;

  finalNumberTest: any;
  finalNumberOperations: any;
  finalResponse: any;

  constructor(
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private appService: AppService
  ) {
    this.testCaseFormGroup = this.formBuilder.group({
      tcNumber: Number
    });
    this.testDataFormGroup = this.formBuilder.group({
      pNumber: Number,
      optNumber: Number
    });
    this.testOperationFormGroup = this.formBuilder.group({
      opt: String
    });
    this.initApp();
  }

  start() {
    this.appService.getCubeSum().subscribe(data => {
      if (data) {
        this.dataTest = {
          numberTest: data[0].numberTest,
          numberPoints: data[0].numberPoints,
          numberOperations: data[0].numberOpertations
        };
        this.showDashboard = false;
        this.showInputs = true;
        this.showInputTestCase = true;
      } else {
        this.toastr.error("Data not found!", "Error", {
          timeOut: 3000
        });
      }
    });
  }

  restart() {
    this.appService.putCubeSumRestart().subscribe(data => {
      if (data) {
        this.dataTest = data;
        this.finalNumberTest = 0;
        this.finalNumberOperations = 0;
        this.finalResponse = [];
        this.showInputTestCase = true;
        this.showInputTestData = false;
        this.showInputTestOperation = false;
        this.showFinalMessages = false;
      } else {
        this.toastr.error("Data not found!", "Error", {
          timeOut: 3000
        });
      }
    });
    this.clearForm();
  }

  initApp() {
    this.dataTest = {};
    this.showDashboard = true;
    this.showInputs = false;
    this.showInputTestData = false;
    this.showInputTestCase = false;
    this.showInputTestOperation = false;
    this.showFinalMessages = false;
    this.finalNumberTest = 0;
    this.finalNumberOperations = 0;
    this.finalResponse = [];
    this.appService.putCubeSumRestart().subscribe();
    this.clearForm();
  }

  addTestCaseNumber(data) {
    if (data.tcNumber < 1) {
      this.toastr.error("Invalid test case number!", "Error", {
        timeOut: 3000
      });
      return;
    } else {
      this.appService
        .putCubeSumTestCaseNumber(data.tcNumber)
        .subscribe(response => {
          if (response) {
            this.dataTest = response;
            this.showInputTestCase = false;
            this.showInputTestData = true;
          } else {
            this.toastr.error("Data not found!", "Error", {
              timeOut: 3000
            });
          }
        });
      this.clearForm();
    }
  }

  addTestData(data) {
    if (data.pNumber < 1) {
      this.toastr.error("Invalid cell deep number!", "Error", {
        timeOut: 3000
      });
      return;
    } else if (data.optNumber < 1) {
      this.toastr.error("Invalid operations number!", "Error", {
        timeOut: 3000
      });
      return;
    } else {
      this.appService.putCubeSumTestData(data).subscribe(response => {
        if (response) {
          this.dataTest = response;
          this.showInputTestData = false;
          this.showInputTestOperation = true;
        } else {
          this.toastr.error("Data not found!", "Error", {
            timeOut: 3000
          });
        }
      });
    }
  }

  doOperation(data) {
    this.appService.postCubeTestOperation(data).subscribe(response => {
      if (response) {
        this.dataTest = response;
        let res = this.dataTest.response;
        this.finalResponse.unshift(res);
        if (res.includes("Point update") || res.includes("0") || Number(res)) {
          this.finalNumberOperations++;
        }
        if (
          this.finalNumberOperations == this.dataTest.numberOperations &&
          this.finalNumberTest < this.dataTest.numberTest
        ) {
          this.appService.getCubeSumMatrixRestart().subscribe();
          this.showInputTestData = true;
          this.showInputTestOperation = false;
          this.finalNumberTest++;
          this.finalNumberOperations = 0;
        }
        if (this.finalNumberTest == this.dataTest.numberTest) {
          this.showFinalMessages = true;
          this.showInputTestOperation = false;
          this.showInputTestData = false;
          this.showInputTestCase = false;
        }
        this.clearForm();
      } else {
        this.toastr.error("Data not found!", "Error", {
          timeOut: 3000
        });
      }
    });
  }

  clearForm() {
    this.testCaseFormGroup.reset();
    this.testDataFormGroup.reset();
    this.testOperationFormGroup.reset();
  }
}
