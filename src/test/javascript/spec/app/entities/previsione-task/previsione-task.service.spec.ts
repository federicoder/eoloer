import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PrevisioneTaskService } from 'app/entities/previsione-task/previsione-task.service';
import { IPrevisioneTask, PrevisioneTask } from 'app/shared/model/previsione-task.model';

describe('Service Tests', () => {
  describe('PrevisioneTask Service', () => {
    let injector: TestBed;
    let service: PrevisioneTaskService;
    let httpMock: HttpTestingController;
    let elemDefault: IPrevisioneTask;
    let expectedResult: IPrevisioneTask | IPrevisioneTask[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PrevisioneTaskService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new PrevisioneTask(0, 0, 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PrevisioneTask', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new PrevisioneTask()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PrevisioneTask', () => {
        const returnedFromService = Object.assign(
          {
            idTaskRef: 1,
            qntOrdine: 1,
            prcPrevisione: 1,
            checkList: 1,
            idTaskMilestone: 1,
            tipoTask: 'BBBBBB',
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

      it('should return a list of PrevisioneTask', () => {
        const returnedFromService = Object.assign(
          {
            idTaskRef: 1,
            qntOrdine: 1,
            prcPrevisione: 1,
            checkList: 1,
            idTaskMilestone: 1,
            tipoTask: 'BBBBBB',
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

      it('should delete a PrevisioneTask', () => {
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
