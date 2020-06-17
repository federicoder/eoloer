import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InvitatoService } from 'app/entities/invitato/invitato.service';
import { IInvitato, Invitato } from 'app/shared/model/invitato.model';

describe('Service Tests', () => {
  describe('Invitato Service', () => {
    let injector: TestBed;
    let service: InvitatoService;
    let httpMock: HttpTestingController;
    let elemDefault: IInvitato;
    let expectedResult: IInvitato | IInvitato[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InvitatoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Invitato(0, 0, 0, 'AAAAAAA', 0, 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Invitato', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Invitato()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Invitato', () => {
        const returnedFromService = Object.assign(
          {
            idInvitato: 1,
            idInvitoRef: 1,
            tokenInvito: 'BBBBBB',
            canalePrimarioInvito: 1,
            canaleBackupInvito: 1,
            statoInvito: 1,
            idUserInvitato: 1,
            idPersonaInvitata: 1,
            nomeUserInvitato: 'BBBBBB',
            dataRispostaInvito: 'BBBBBB',
            ruoloInvitato: 1,
            indInvitati: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Invitato', () => {
        const returnedFromService = Object.assign(
          {
            idInvitato: 1,
            idInvitoRef: 1,
            tokenInvito: 'BBBBBB',
            canalePrimarioInvito: 1,
            canaleBackupInvito: 1,
            statoInvito: 1,
            idUserInvitato: 1,
            idPersonaInvitata: 1,
            nomeUserInvitato: 'BBBBBB',
            dataRispostaInvito: 'BBBBBB',
            ruoloInvitato: 1,
            indInvitati: 1,
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

      it('should delete a Invitato', () => {
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
