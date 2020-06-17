import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AllegatoTaskService } from 'app/entities/allegato-task/allegato-task.service';
import { IAllegatoTask, AllegatoTask } from 'app/shared/model/allegato-task.model';

describe('Service Tests', () => {
  describe('AllegatoTask Service', () => {
    let injector: TestBed;
    let service: AllegatoTaskService;
    let httpMock: HttpTestingController;
    let elemDefault: IAllegatoTask;
    let expectedResult: IAllegatoTask | IAllegatoTask[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AllegatoTaskService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AllegatoTask(0, 0, 0, 0, 0, 'AAAAAAA', 0, 0, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AllegatoTask', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new AllegatoTask()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AllegatoTask', () => {
        const returnedFromService = Object.assign(
          {
            idAllegatoTask: 1,
            idTipo: 1,
            idTask: 1,
            formato: 1,
            note: 'BBBBBB',
            stato: 1,
            pubblico: 1,
            version: 'BBBBBB',
            idAllegatoMaster: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AllegatoTask', () => {
        const returnedFromService = Object.assign(
          {
            idAllegatoTask: 1,
            idTipo: 1,
            idTask: 1,
            formato: 1,
            note: 'BBBBBB',
            stato: 1,
            pubblico: 1,
            version: 'BBBBBB',
            idAllegatoMaster: 1,
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

      it('should delete a AllegatoTask', () => {
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
