import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { ConsuntivoTaskUpdateComponent } from 'app/entities/consuntivo-task/consuntivo-task-update.component';
import { ConsuntivoTaskService } from 'app/entities/consuntivo-task/consuntivo-task.service';
import { ConsuntivoTask } from 'app/shared/model/consuntivo-task.model';

describe('Component Tests', () => {
  describe('ConsuntivoTask Management Update Component', () => {
    let comp: ConsuntivoTaskUpdateComponent;
    let fixture: ComponentFixture<ConsuntivoTaskUpdateComponent>;
    let service: ConsuntivoTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [ConsuntivoTaskUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ConsuntivoTaskUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConsuntivoTaskUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsuntivoTaskService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ConsuntivoTask(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ConsuntivoTask();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
