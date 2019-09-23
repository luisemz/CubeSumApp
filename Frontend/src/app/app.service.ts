import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class AppService {
  private addressApi: String;

  constructor(private http: HttpClient) {
    this.addressApi = "http://localhost:8080";
  }

  getCubeSum() {
    return this.http.get(`${this.addressApi}/cubesum`);
  }

  putCubeSumTestCaseNumber(testCaseNumber: Number) {
    return this.http.put(`${this.addressApi}/cubesum/start`, {
      numberTest: testCaseNumber
    });
  }

  putCubeSumTestData(testData: any) {
    return this.http.put(`${this.addressApi}/cubesum/ponitsAndOperations`, {
      numberPoints: testData.pNumber,
      numberOperations: testData.optNumber
    });
  }

  postCubeTestOperation(operation: any) {
    return this.http.post(`${this.addressApi}/cubesum/operation`, {
      operation: operation.opt.toUpperCase()
    });
  }

  putCubeSumRestart() {
    return this.http.put(`${this.addressApi}/cubesum/restart`, {});
  }

  getCubeSumMatrixRestart() {
    return this.http.get(`${this.addressApi}/cubesum/matrix/restart`);
  }
}
