import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EoloprjTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { RisorseDisponibiliDeleteDialogComponent } from 'app/entities/risorse-disponibili/risorse-disponibili-delete-dialog.component';
import { RisorseDisponibiliService } from 'app/entities/risorse-disponibili/risorse-disponibili.service';

describe('Component Tests', () => {
  describe('RisorseDisponibili Management Delete Component', () => {
    let comp: RisorseDisponibiliDeleteDialogComponent;
    let fixture: ComponentFixture<RisorseDisponibiliDeleteDialogComponent>;
    let service: RisorseDisponibiliService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [RisorseDisponibiliDeleteDialogComponent],
      })
        .overrideTemplate(RisorseDisponibiliDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RisorseDisponibiliDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RisorseDisponibiliService);
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
