import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InvitoEventoService } from 'app/entities/invito-evento/invito-evento.service';
import { IInvitoEvento, InvitoEvento } from 'app/shared/model/invito-evento.model';

describe('Service Tests', () => {
  describe('InvitoEvento Service', () => {
    let injector: TestBed;
    let service: InvitoEventoService;
    let httpMock: HttpTestingController;
    let elemDefault: IInvitoEvento;
    let expectedResult: IInvitoEvento | IInvitoEvento[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InvitoEventoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new InvitoEvento(0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a InvitoEvento', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new InvitoEvento()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a InvitoEvento', () => {
        const returnedFromService = Object.assign(
          {
            idTaskRef: 1,
            luogoFisico: 'BBBBBB',
            indicazioniLuogo: 'BBBBBB',
            dataInizio: 'BBBBBB',
            oraInizio: 'BBBBBB',
            dataFine: 'BBBBBB',
            oraFine: 'BBBBBB',
            urlStanzaVirtuale: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of InvitoEvento', () => {
        const returnedFromService = Object.assign(
          {
            idTaskRef: 1,
            luogoFisico: 'BBBBBB',
            indicazioniLuogo: 'BBBBBB',
            dataInizio: 'BBBBBB',
            oraInizio: 'BBBBBB',
            dataFine: 'BBBBBB',
            oraFine: 'BBBBBB',
            urlStanzaVirtuale: 'BBBBBB',
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

      it('should delete a InvitoEvento', () => {
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
