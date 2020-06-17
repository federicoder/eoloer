import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { IndirizzoPersonaService } from 'app/entities/indirizzo-persona/indirizzo-persona.service';
import { IIndirizzoPersona, IndirizzoPersona } from 'app/shared/model/indirizzo-persona.model';

describe('Service Tests', () => {
  describe('IndirizzoPersona Service', () => {
    let injector: TestBed;
    let service: IndirizzoPersonaService;
    let httpMock: HttpTestingController;
    let elemDefault: IIndirizzoPersona;
    let expectedResult: IIndirizzoPersona | IIndirizzoPersona[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(IndirizzoPersonaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new IndirizzoPersona(0, 0, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a IndirizzoPersona', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new IndirizzoPersona()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a IndirizzoPersona', () => {
        const returnedFromService = Object.assign(
          {
            idPersona: 1,
            indirizzo: 'BBBBBB',
            comune: 'BBBBBB',
            cap: 1,
            provincia: 'BBBBBB',
            regione: 'BBBBBB',
            nazione: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of IndirizzoPersona', () => {
        const returnedFromService = Object.assign(
          {
            idPersona: 1,
            indirizzo: 'BBBBBB',
            comune: 'BBBBBB',
            cap: 1,
            provincia: 'BBBBBB',
            regione: 'BBBBBB',
            nazione: 'BBBBBB',
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

      it('should delete a IndirizzoPersona', () => {
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
