import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InvitoService } from 'app/entities/invito/invito.service';
import { IInvito, Invito } from 'app/shared/model/invito.model';

describe('Service Tests', () => {
  describe('Invito Service', () => {
    let injector: TestBed;
    let service: InvitoService;
    let httpMock: HttpTestingController;
    let elemDefault: IInvito;
    let expectedResult: IInvito | IInvito[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InvitoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Invito(
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Invito', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Invito()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Invito', () => {
        const returnedFromService = Object.assign(
          {
            idStudioProfessionaleRef: 1,
            dataInvito: 'BBBBBB',
            idUserInvitante: 1,
            nomeUserInvitante: 'BBBBBB',
            dataScadenzaInvito: 'BBBBBB',
            testoInvito: 'BBBBBB',
            idPraticaRef: 1,
            idTaskRef: 1,
            luogoFisico: 'BBBBBB',
            indicazioniLuogo: 'BBBBBB',
            dataInizio: 'BBBBBB',
            oraInizio: 'BBBBBB',
            dataFine: 'BBBBBB',
            oraFine: 'BBBBBB',
            urlStanzaVirtuale: 'BBBBBB',
            discriminator: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Invito', () => {
        const returnedFromService = Object.assign(
          {
            idStudioProfessionaleRef: 1,
            dataInvito: 'BBBBBB',
            idUserInvitante: 1,
            nomeUserInvitante: 'BBBBBB',
            dataScadenzaInvito: 'BBBBBB',
            testoInvito: 'BBBBBB',
            idPraticaRef: 1,
            idTaskRef: 1,
            luogoFisico: 'BBBBBB',
            indicazioniLuogo: 'BBBBBB',
            dataInizio: 'BBBBBB',
            oraInizio: 'BBBBBB',
            dataFine: 'BBBBBB',
            oraFine: 'BBBBBB',
            urlStanzaVirtuale: 'BBBBBB',
            discriminator: 'BBBBBB',
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

      it('should delete a Invito', () => {
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
