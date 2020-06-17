import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AssegnazioneTaskService } from 'app/entities/assegnazione-task/assegnazione-task.service';
import { IAssegnazioneTask, AssegnazioneTask } from 'app/shared/model/assegnazione-task.model';

describe('Service Tests', () => {
  describe('AssegnazioneTask Service', () => {
    let injector: TestBed;
    let service: AssegnazioneTaskService;
    let httpMock: HttpTestingController;
    let elemDefault: IAssegnazioneTask;
    let expectedResult: IAssegnazioneTask | IAssegnazioneTask[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AssegnazioneTaskService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AssegnazioneTask(0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AssegnazioneTask', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new AssegnazioneTask()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AssegnazioneTask', () => {
        const returnedFromService = Object.assign(
          {
            idAttivita: 1,
            idUserAmmesso: 1,
            ruolo: 1,
            idUserConcedente: 1,
            statoAssegnazione: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AssegnazioneTask', () => {
        const returnedFromService = Object.assign(
          {
            idAttivita: 1,
            idUserAmmesso: 1,
            ruolo: 1,
            idUserConcedente: 1,
            statoAssegnazione: 1,
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

      it('should delete a AssegnazioneTask', () => {
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
