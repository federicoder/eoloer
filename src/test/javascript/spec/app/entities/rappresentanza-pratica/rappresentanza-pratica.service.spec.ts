import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RappresentanzaPraticaService } from 'app/entities/rappresentanza-pratica/rappresentanza-pratica.service';
import { IRappresentanzaPratica, RappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';
import { Ruoli } from 'app/shared/model/enumerations/ruoli.model';

describe('Service Tests', () => {
  describe('RappresentanzaPratica Service', () => {
    let injector: TestBed;
    let service: RappresentanzaPraticaService;
    let httpMock: HttpTestingController;
    let elemDefault: IRappresentanzaPratica;
    let expectedResult: IRappresentanzaPratica | IRappresentanzaPratica[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RappresentanzaPraticaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RappresentanzaPratica(0, 0, 0, Ruoli.TDP);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RappresentanzaPratica', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new RappresentanzaPratica()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RappresentanzaPratica', () => {
        const returnedFromService = Object.assign(
          {
            idRuoloPersona: 1,
            idPersonaRef: 1,
            ruoli: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RappresentanzaPratica', () => {
        const returnedFromService = Object.assign(
          {
            idRuoloPersona: 1,
            idPersonaRef: 1,
            ruoli: 'BBBBBB',
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

      it('should delete a RappresentanzaPratica', () => {
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
