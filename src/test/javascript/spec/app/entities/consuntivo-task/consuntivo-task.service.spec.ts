import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ConsuntivoTaskService } from 'app/entities/consuntivo-task/consuntivo-task.service';
import { IConsuntivoTask, ConsuntivoTask } from 'app/shared/model/consuntivo-task.model';

describe('Service Tests', () => {
  describe('ConsuntivoTask Service', () => {
    let injector: TestBed;
    let service: ConsuntivoTaskService;
    let httpMock: HttpTestingController;
    let elemDefault: IConsuntivoTask;
    let expectedResult: IConsuntivoTask | IConsuntivoTask[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ConsuntivoTaskService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ConsuntivoTask(0, 0, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ConsuntivoTask', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ConsuntivoTask()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ConsuntivoTask', () => {
        const returnedFromService = Object.assign(
          {
            idTaskRef: 1,
            dataInizio: 'BBBBBB',
            dataFine: 'BBBBBB',
            timeLine: 1,
            version: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ConsuntivoTask', () => {
        const returnedFromService = Object.assign(
          {
            idTaskRef: 1,
            dataInizio: 'BBBBBB',
            dataFine: 'BBBBBB',
            timeLine: 1,
            version: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ConsuntivoTask', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
