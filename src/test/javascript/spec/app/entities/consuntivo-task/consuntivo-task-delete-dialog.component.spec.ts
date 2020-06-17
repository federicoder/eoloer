import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EoloprjTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ConsuntivoTaskDeleteDialogComponent } from 'app/entities/consuntivo-task/consuntivo-task-delete-dialog.component';
import { ConsuntivoTaskService } from 'app/entities/consuntivo-task/consuntivo-task.service';

describe('Component Tests', () => {
  describe('ConsuntivoTask Management Delete Component', () => {
    let comp: ConsuntivoTaskDeleteDialogComponent;
    let fixture: ComponentFixture<ConsuntivoTaskDeleteDialogComponent>;
    let service: ConsuntivoTaskService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [ConsuntivoTaskDeleteDialogComponent],
      })
        .overrideTemplate(ConsuntivoTaskDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConsuntivoTaskDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsuntivoTaskService);
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
