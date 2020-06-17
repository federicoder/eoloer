import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PersonaFisicaService } from 'app/entities/persona-fisica/persona-fisica.service';
import { IPersonaFisica, PersonaFisica } from 'app/shared/model/persona-fisica.model';

describe('Service Tests', () => {
  describe('PersonaFisica Service', () => {
    let injector: TestBed;
    let service: PersonaFisicaService;
    let httpMock: HttpTestingController;
    let elemDefault: IPersonaFisica;
    let expectedResult: IPersonaFisica | IPersonaFisica[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PersonaFisicaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new PersonaFisica(0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PersonaFisica', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new PersonaFisica()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PersonaFisica', () => {
        const returnedFromService = Object.assign(
          {
            idPersonaFisica: 1,
            idPersonaRef: 1,
            idRuoloPersonaRef: 1,
            titolo: 'BBBBBB',
            cognome: 'BBBBBB',
            nome: 'BBBBBB',
            dataDiNascita: 'BBBBBB',
            luogoDiNascita: 'BBBBBB',
            professione: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PersonaFisica', () => {
        const returnedFromService = Object.assign(
          {
            idPersonaFisica: 1,
            idPersonaRef: 1,
            idRuoloPersonaRef: 1,
            titolo: 'BBBBBB',
            cognome: 'BBBBBB',
            nome: 'BBBBBB',
            dataDiNascita: 'BBBBBB',
            luogoDiNascita: 'BBBBBB',
            professione: 'BBBBBB',
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

      it('should delete a PersonaFisica', () => {
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
