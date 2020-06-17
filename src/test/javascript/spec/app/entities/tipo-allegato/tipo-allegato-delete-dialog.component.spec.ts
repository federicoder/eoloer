import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EoloprjTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TipoAllegatoDeleteDialogComponent } from 'app/entities/tipo-allegato/tipo-allegato-delete-dialog.component';
import { TipoAllegatoService } from 'app/entities/tipo-allegato/tipo-allegato.service';

describe('Component Tests', () => {
  describe('TipoAllegato Management Delete Component', () => {
    let comp: TipoAllegatoDeleteDialogComponent;
    let fixture: ComponentFixture<TipoAllegatoDeleteDialogComponent>;
    let service: TipoAllegatoService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TipoAllegatoDeleteDialogComponent],
      })
        .overrideTemplate(TipoAllegatoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoAllegatoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoAllegatoService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
